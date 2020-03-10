package mysqls

const (
	TableUser                  = "user"
	MysqlDuplicateKeyErrNumber = 1062
)

type User struct {
	ID   int    `db:"id" gorm:"column:id"`
	UID  string `db:"uid" gorm:"column:uid"`
	Name string `db:"name" gorm:"column:name"`

	CTime int64 `db:"ctime" gorm:"column:ctime"`
	MTime int64 `db:"mtime" gorm:"column:mtime"`
}

type Membership struct {
	ID         int    `db:"id" gorm:"column:id"`
	UID        string `db:"uid" gorm:"column:uid"`
	CuteAvatar string `db:"cute_avatar" gorm:"column:cute_avatar"`

	CTime int64 `db:"ctime" gorm:"column:ctime"`
	MTime int64 `db:"mtime" gorm:"column:mtime"`
}
