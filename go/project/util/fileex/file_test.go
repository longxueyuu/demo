package fileex

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"testing"
)

type Meta struct {
	Name      string `json:"name"`
	MaxLength int    `json:"max_length"`
}

func TestJSONFile(t *testing.T) {
	path := "meta.json"
	meta, err := parseToMeta(path)
	if err != nil {
		log.Printf("parse json, err=%v", err)
		return
	}
	log.Printf("meta=%+v", meta)
}

func parseToMeta(path string) (*Meta, error) {
	bs, err := ioutil.ReadFile(path)
	if err != nil {
		return nil, err
	}
	meta := Meta{}
	err = json.Unmarshal(bs, &meta)
	return &meta, err
}
