package mysqls

import (
	"database/sql"
	"fmt"
	"upper.io/db.v3/lib/sqlbuilder"
	"upper.io/db.v3/mysql"
)

type MySQLConf struct {
	Host     string `yaml:"host"`
	Port     int    `yaml:"port"`
	DB       string `yaml:"db"`
	User     string `yaml:"user"`
	Password string `yaml:"password"`
}

func (conf MySQLConf) DSN(readTimeOut int64) string {
	if readTimeOut <= 0 {
		readTimeOut = 20
	}
	return fmt.Sprintf(
		"%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=true&loc=Local&timeout=5s&readTimeout=%vms",
		conf.User, conf.Password, conf.Host, conf.Port, conf.DB, readTimeOut)
}

func (conf *MySQLConf) GenClient() (sqlbuilder.Database, error) {
	dsn, err := mysql.ParseURL(conf.DSN(5000))
	if err != nil {
		return nil, err
	}
	return mysql.Open(dsn)
}

func (conf *MySQLConf) GenClient2(readTimeout int64) (sqlbuilder.Database, error) {
	dsn, err := mysql.ParseURL(conf.DSN(readTimeout))
	if err != nil {
		return nil, err
	}
	return mysql.Open(dsn)
}

func (conf *MySQLConf) GenSqlDB() (*sql.DB, error) {
	return sql.Open("mysql", conf.DSN(0))
}
