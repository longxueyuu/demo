package util

import (
	"log"
	"testing"
)

type Type int

func TestType(t *testing.T) {
	ids := []int{1, 2, 3}
	ids = append(ids, 4)
	//Get(append(interface{}{}(nil), ids...))
}

func Get(ids ...interface{}) {
	log.Printf("ids=%v", ids)
}
