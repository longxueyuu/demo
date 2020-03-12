package config

type Demo struct {
	Env  string `yaml:"env"`
	Bind string `yaml:"bind"`
	Log  Log    `yaml:"log"`
}

type Log struct {
	Prefix string `yaml:"prefix"`
	Api    string `yaml:"api"`
	Err    string `yaml:"err"`
	Trace  string `yaml:"trace"`
}
