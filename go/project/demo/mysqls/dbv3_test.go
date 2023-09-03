package mysqls

import (
	"context"
	"database/sql"
	"fmt"
	"log"
	"testing"
	"time"
	"upper.io/db.v3"
	"upper.io/db.v3/lib/sqlbuilder"
)

var (
	cli sqlbuilder.Database

	cli2 sqlbuilder.Database
)

func init() {
	conf := &MySQLConf{
		Host:     "localhost",
		Port:     3310,
		DB:       "test",
		User:     "root",
		Password: "",
	}

	var err error
	cli, err = conf.GenClient()
	log.Printf("init, err=%v", err)
	cli.SetConnMaxLifetime(300 * time.Second)
	cli.SetLogging(true)
	cli.SetMaxOpenConns(10)
	cli.LoggingEnabled()

	cli2, err = conf.GenClient2(1000)
	log.Printf("init, err=%v", err)
	cli2.SetConnMaxLifetime(300 * time.Second)
	cli2.SetLogging(true)
	cli2.SetMaxOpenConns(30)
	cli2.LoggingEnabled()
}

func forUpdate(queryIn string) string {
	return fmt.Sprintf("%s for update", queryIn)
}

func TestConnLifeTime(t *testing.T) {
	pool := cli.Driver().(*sql.DB)
	log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())

	ws := make([]*Wallet, 0)
	log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
	tx, err := cli.NewTx(nil)
	if err != nil {
		log.Printf("new tx, err=%v", err)
		return
	}

	err = tx.SelectFrom("wallet").Where("uid = ?", "uidxx").Amend(forUpdate).All(&ws)

	if err != nil {
		log.Printf("TestSelectCollate: err=%v", err)
		tx.Rollback()
	}

	log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())

	buffer := make(chan struct{}, 200)
	for i := 0; i < 10000000; i++ {
		buffer <- struct{}{}
		//go func() {
		ws := make([]*Wallet, 0)

		log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
		tx, err := cli.NewTx(nil)
		if err != nil {
			log.Printf("new tx, err=%v", err)
			return
		}

		log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
		err = tx.SelectFrom("wallet").Where("uid = ?", "uidxx").Amend(forUpdate).All(&ws)
		log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
		if err != nil {
			log.Printf("TestConnLifeTime: i=%v err=%v", i, err)
			log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
			err = tx.Rollback()
			if err != nil {
				log.Printf("TestConnLifeTime: rollback, err=%v", err)
			}
		} else {
			err = tx.Commit()
			if err != nil {
				log.Printf("TestConnLifeTime: commit, err=%v", err)
			}
		}

		<-buffer
		//}()

		log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
	}

}

func TestSave(t *testing.T) {
	log.Printf("TestSave: init")
	time.Sleep(10 * time.Second)
	log.Printf("TestSave: start")

	buffer := make(chan struct{}, 100)
	for i := 0; i < 100; i++ {
		buffer <- struct{}{}
		go func() {
			log.Printf("TestSave")
			ms := make([]Membership, 0)
			err := cli2.SelectFrom("membership").Columns(db.Raw("ifnull(sum(id), 0) as id"), "uid").
				Where("uid != ''").GroupBy("uid").All(&ms)
			if err != nil {
				log.Printf("TestSave: err=%v", err)
			}
			<-buffer
		}()
	}

	s := make(chan int, 0)
	<-s
}

func TestGroupBy(t *testing.T) {
	pool := cli.Driver().(*sql.DB)
	log.Printf("TestConnLifeTime: pool, stats=%+v", pool.Stats())
	ms := make([]Membership, 0)
	err := cli.SelectFrom("membership").Columns(db.Raw("ifnull(sum(id), 0) as id"), "uid").
		Where("uid != ''").GroupBy("uid").All(&ms)
	if err != nil {
		t.Fatal(err)
	}

	log.Printf("ms=%v", ms)
}

func TestSelectCount(t *testing.T) {
	x := "u1234"
	u := Membership{}
	count, err := cli.Collection("membership").Find("uid = ? and type = ? ", x, 0).Count()
	if err != nil {
		log.Printf("TestSelectCollate: count=%v err=%v", count, err)
		return
	}
	log.Printf("TestSelectCollate: u=%v count=%v", u, count)
}

func TestUpdateExpr(t *testing.T) {
	k2v := map[string]interface{}{
		"pay_type":    db.Raw("( `pay_type` & ? ) | ?", 13, 2),
		"cute_avatar": "ca_test",
	}

	r, err := cli.Update("membership").Set(k2v).Where("uid = ?", "u1234").Exec()
	if err != nil {
		log.Printf("TestUpdateExpr: err=%v", err)
		return
	}
	log.Printf("TestUpdateExpr: r=%v", r)
}

func TestSelectForUpdate(t *testing.T) {
	uid := "u1235"
	c, cf := context.WithTimeout(context.Background(), 10*time.Second)
	defer cf()
	tx, err := cli.NewTx(c)
	if err != nil {
		log.Printf("TestSelectForUpdate: err=%v", err)
		return
	}

	u := Membership{}
	err = tx.SelectFrom("membership").Columns("uid").Where("uid = ?", uid).Join().Amend(forUpdate).One(&u)
	if err != nil && err != db.ErrNoMoreRows {
		log.Printf("TestSelectForUpdate: err=%v", err)
		return
	}
	tx.Commit()
	log.Printf("TestSelectForUpdate: u=%v", u)
}

func TestGetUser(t *testing.T) {
	var u User

	err := cli.SelectFrom(TableUser).Where("uid = ?", "2").One(&u)
	if err != nil {
		panic(err)
	}

	log.Printf("u=%+v", u)

}

func TestBatchInsert(t *testing.T) {
	u := &User{UID: "u11", Name: "insert"}
	u2 := &User{UID: "u10", Name: "insert"}
	r, err := cli.InsertInto(TableUser).Values(u).Values(u2).Exec()
	if err != nil {
		log.Printf("TestBatchInsert: err=%v", err)
	}
	log.Printf("TestBatchInsert: r=%v", r)

	sql := "INSERT IGNORE INTO `user` (`uid`, `name`) VALUES (?, ?), (?, ?)"
	result, err := cli.Exec(sql, u.UID, u.Name, u2.UID, u2.Name)
	log.Printf("TestBatchInsert: r=%v", result)

	var users []*User
	log.Printf("TestBatchInsert: users=%v", users)

	err = cli.Collection(TableUser).Find().All(&users)
	if err != nil {
		log.Printf("TestBatchInsert: err=%v", err)
		return
	}

	for _, u := range users {
		log.Printf("TestBatchInsert: user=%v", *u)
	}

}
