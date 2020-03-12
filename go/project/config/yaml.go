package config

import (
	"gopkg.in/yaml.v2"
	"io/ioutil"
)

func ParseYML(path string, v interface{}) error {
	bs, err := ioutil.ReadFile(path)
	if err != nil {
		return err
	}

	return yaml.Unmarshal(bs, v)
}
