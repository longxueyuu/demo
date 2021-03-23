package main

import (
	"database/sql"
	"fmt"
	"github.com/bradfitz/gomemcache/memcache"
	"net/http"
	_ "net/http/pprof"
	"sync"
	"time"
	"upper.io/db.v3/lib/sqlbuilder"
	"upper.io/db.v3/mysql"
)

type DemoDAL struct {
	dbCli sqlbuilder.Database
	mcCli *memcache.Client
}

func NewDemoDAL() *DemoDAL {
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=true&loc=Local&readTimeout=1s&timeout=15s", "root", "", "localhost", 3306, "test")
	connURL, err := mysql.ParseURL(dsn)
	if err != nil {
		panic("mysql.ParseURL")
	}
	dbCli, err := mysql.Open(connURL)
	if err != nil {
		panic("mysql.Open")
	}

	dbCli.SetConnMaxLifetime(time.Minute)
	dbCli.SetMaxOpenConns(10)
	//dbCli.SetLogging(true)

	mcAddrs := []string{"localhost:11211"}
	mcCli := memcache.New(mcAddrs...)
	mcCli.MaxIdleConns = 10

	return &DemoDAL{
		dbCli: dbCli,
		mcCli: mcCli,
	}
}

func (dal *DemoDAL) run() {
	obj := make([]map[string]interface{}, 0)
	err := dal.dbCli.SelectFrom("membership").All(&obj)
	//sqlDB := dal.dbCli.Driver().(*sql.DB)
	//stats := sqlDB.Stats()
	//println("db stats", fmt.Sprintf("%+v", stats))
	if err != nil {
		println(fmt.Sprintf("%+v", err))
	}
	//println("db info", obj)
	//
	//item, err := dal.mcCli.Get("test")
	//if err != nil && err != memcache.ErrCacheMiss {
	//	println(err.Error())
	//}
	//if item == nil {
	//	println("mc info", "mc key miss")
	//} else {
	//	println("mc info", string(item.Value))
	//}
	//time.Sleep(time.Second)
}

func (dal *DemoDAL) runSqlScan(i int) {
	sq := "select count(distinct uid) as count from membership where id > 0"
	rows, err := dal.dbCli.Query(sq)
	if err != nil {
		println(fmt.Sprintf("runSqlScan: query, err=%v", err))
	}

	sqlDB := dal.dbCli.Driver().(*sql.DB)
	stats := sqlDB.Stats()
	println("db stats", fmt.Sprintf("%+v", stats))
	println(fmt.Sprintf("runSqlScan, i=%v", i))
	println("")

	var count int64
	for rows.Next() {
		err = rows.Scan(&count)
		if err != nil {
			println(fmt.Sprintf("CountUserReportsByUID: rows.Scan, err=%v", err))
			return
		}
		// rows.Next() 循环里提前return会导致连接泄露，应该在循环前执行defer rows.Close()
		// return
	}
}

func main() {
	print("hello world")
	group := sync.WaitGroup{}
	demoDAL := NewDemoDAL()
	for i := 0; i < 1; i++ {
		group.Add(1)
		go func() {
			//demoDAL.run()
			group.Done()
		}()
	}

	// test runSqlScan
	group.Add(1)
	go func() {
		for i := 0; i < 200; i++ {
			demoDAL.runSqlScan(i)
		}
		group.Done()
	}()
	group.Wait()
	println("init done")
	err := http.ListenAndServe(":12345", nil)
	if err != nil {
		panic(err.Error())
	}
}
