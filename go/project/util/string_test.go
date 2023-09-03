package util

import (
	"encoding/hex"
	"github.com/google/uuid"
	"log"
	"reflect"
	"strings"
	"testing"
)

func TestRepeat(t *testing.T) {
	s := "(?, ?), "
	n := 3

	ss := strings.Repeat(s, n)
	ss = strings.Trim(ss, ", ")
	log.Printf(ss)

	u, _ := uuid.NewUUID()
	log.Printf(u.String())

	bs, _ := hex.DecodeString("abcdefg12345")
	for _, x := range bs {
		log.Printf("pattern=%q %v", x, reflect.TypeOf(x))
	}
}

func TestStr(t *testing.T) {
	a := len("")
	log.Printf("%v", a)
}

func TestAdd(t *testing.T) {
	var xs []int = nil
	printx(xs...)
}

func printx(xs ...int) {
	log.Printf("xs=%v", xs)
}

type Node struct {
	next *Node
	val  int
}

func TestVe(t *testing.T) {
	a := &Node{val: 1}
	b := &Node{val: 2, next: a}
	c := &Node{val: 3, next: b}

	var d *Node
	//c.next, c = d, nil // panic
	c, c.next = nil, d
	log.Printf("c=%+v", c)
}
