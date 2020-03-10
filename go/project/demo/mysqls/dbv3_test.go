package mysqls

import (
	"context"
	"fmt"
	"log"
	"testing"
	"time"
	"upper.io/db.v3"
	"upper.io/db.v3/lib/sqlbuilder"
)

var (
	cli sqlbuilder.Database
)

func init() {
	conf := &MySQLConf{
		Host:     "localhost",
		Port:     3306,
		DB:       "test",
		User:     "root",
		Password: "",
	}

	cli, _ = conf.GenClient()
	cli.SetLogging(true)
}

func forUpdate(queryIn string) string {
	return fmt.Sprintf("%s for update", queryIn)
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
	err = tx.SelectFrom("membership").Where("uid = ?", uid).Amend(forUpdate).One(&u)
	if err != nil && err != db.ErrNoMoreRows {
		log.Printf("TestSelectForUpdate: err=%v", err)
		return
	}
	log.Printf("TestSelectForUpdate: u=%v", u)
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
