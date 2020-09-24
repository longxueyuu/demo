package mysqls

import (
	"fmt"
	"github.com/go-sql-driver/mysql"
	_ "github.com/go-sql-driver/mysql"
	"github.com/jinzhu/gorm"
	"log"
	"strconv"
	"testing"
	"time"
)

var (
	dbCli *gorm.DB
)

func init() {
	var err error

	dbCli, err = gorm.Open("mysql", fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=true&loc=Local",
		"root", "", "localhost", 3306, "test"))
	if err != nil {
		log.Printf("init: err=%v", err)
		return
	}

	// extension
	dbCli = dbCli.Set("gorm:query_option", "FOR UPDATE")

	users := make([]*User, 0)
	err = dbCli.Debug().Table(TableUser).Where("id > 0").Find(&users).Error
	if err == gorm.ErrRecordNotFound || len(users) == 0 {
		for i := 0; i < 5; i++ {
			u := &User{
				UID:   "u" + strconv.Itoa(i),
				Name:  "trump" + strconv.Itoa(i),
				CTime: time.Now().Unix(),
				MTime: time.Now().Unix(),
			}
			dbCli.Table(TableUser).Save(u)
		}
	}

	if err != nil {
		log.Printf("init: err=%v", err)
		return
	}
}

func TestInsertOnDuplicate(t *testing.T) {
	users := make([]*User, 0)
	err := dbCli.Debug().Table(TableUser).Where("id > 0").Find(&users).Error
	if err != nil {
		log.Printf("TestInsertOnDuplicate: query, err=%v", err)
	}

	for i, u := range users {
		log.Printf("user[%v]: %v", i, u)
	}

	if len(users) > 0 {
		u := users[0]
		u.ID = 0
		err := dbCli.Debug().Table(TableUser).Save(u).Error
		if isDuplicateKeyErr(err) {
			log.Printf("TestInsertOnDuplicate: duplicate key, err=%v", err)
			return
		}

		log.Printf("TestInsertOnDuplicate: save, err=%v", err)
	}
}

func TestTxSelectForUpdate(t *testing.T) {
	tx := dbCli.Debug().Begin()
	users := make([]*User, 0)
	err := tx.Debug().Table(TableUser).Where("id > 0").Find(&users).Error
	if err != nil {
		log.Printf("TestTxSelectForUpdate: query, err=%v", err)
	}

	for i, u := range users {
		u.Name = "test"
		err := tx.Debug().Table(TableUser).Save(u).Error
		if err != nil {
			log.Printf("TestTxSelectForUpdate: user[%v]: %v err=%v", i, u, err)
			tx.Rollback()
			break
		}
		log.Printf("TestTxSelectForUpdate: user[%v]: %v", i, u)
	}
	tx.Commit()
}

func TestFirstOrCreate(t *testing.T) {
	u := User{
		UID:  "u20",
		Name: "test",
	}
	err := dbCli.Debug().Table(TableUser).FirstOrCreate(&u, "uid=?", u.UID).Error
	if err != nil {
		log.Printf("TestFirstOrCreate: query, err=%v", err)
	}
}

func isDuplicateKeyErr(err error) bool {
	if err != nil {
		mysqlErr, ok := err.(*mysql.MySQLError)
		// It's tricky, but no better way yet
		if ok && mysqlErr.Number == MysqlDuplicateKeyErrNumber {
			return true
		}
	}
	return false
}
