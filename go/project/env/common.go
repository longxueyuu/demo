package env

import "log"

const (
	EnvDev  = "dev"
	EnvTest = "test"
	EnvProd = "prod"
)

var (
	ApiLog *log.Logger
	ErrLog *log.Logger
)

var (
	Env string
)
