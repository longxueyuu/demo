package util

import (
	"log"
	"strings"
	"testing"
)

func TestRepeat(t *testing.T) {
	s := "(?, ?), "
	n := 3

	ss := strings.Repeat(s, n)
	ss = strings.Trim(ss, ", ")
	log.Printf(ss)
}

func TestStr(t *testing.T) {
	a := len("")
	log.Printf("%v", a)
}
