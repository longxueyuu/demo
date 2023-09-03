package mysqls

import "time"

const (
	TableUser                  = "user"
	MysqlDuplicateKeyErrNumber = 1062
)

type User struct {
	ID   int    `db:"id" gorm:"column:id"`
	UID  string `db:"uid" gorm:"column:uid"`
	Name string `db:"name" gorm:"column:name"`

	Extra *Extra

	CTime time.Time `db:"ctime" gorm:"column:ctime"`
	MTime time.Time `db:"mtime" gorm:"column:mtime"`
}

type Extra struct {
	Age         int64  `db:"age"`
	Description string `db:"description"`
}

type Membership struct {
	ID         int    `db:"id" gorm:"column:id"`
	UID        string `db:"uid" gorm:"column:uid"`
	CuteAvatar string `db:"cute_avatar" gorm:"column:cute_avatar"`

	CTime int64 `db:"ctime" gorm:"column:ctime"`
	MTime int64 `db:"mtime" gorm:"column:mtime"`
}

type Wallet struct {
	Id int64 `db:"id" json:"-"`
}
