package mysqls

import (
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

func (conf MySQLConf) DSN() string {
	return fmt.Sprintf(
		"%s:%s@tcp(%s:%d)/%s?charset=utf8mb4&parseTime=true&loc=Local",
		conf.User, conf.Password, conf.Host, conf.Port, conf.DB)
}

func (conf *MySQLConf) GenClient() (sqlbuilder.Database, error) {
	dsn, err := mysql.ParseURL(conf.DSN())
	if err != nil {
		return nil, err
	}
	return mysql.Open(dsn)
}
