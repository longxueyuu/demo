package util

import (
	"log"
	"testing"
)

func TestNil(t *testing.T) {
	i := interface{}(nil)
	if i == nil {
		log.Printf("i=%v ", i)
	}

	m, ok := i.(map[string]interface{})
	log.Printf("i=%v m=%v, ok=%v", i, m, ok)

	v, ok := m["key"]
	log.Printf("v=%v ok=%v", v, ok)

	m2 := make(map[string]string)
	m2 = nil
	log.Printf("m2=%v ", m2)

	v, ok = m2["key"]
	log.Printf("v=%v ok=%v", v, ok)
}

func TestPointerPrint(t *testing.T) {
	a := &struct {
		Key string
	}{Key: "this is the value"}
	log.Printf("v=%v s=%s", a, a)
}

type St struct {
}

func TestNilAddr(t *testing.T) {
	var s *St
	log.Printf("nil var, s=%p", &s)

	size := 0

	data := make([]byte, size)

	bs := data[:size]
	log.Printf("bs=%v", bs)
	printBs(bs)
}

func printBs(data []byte) {
	i := len(data)
	_ = i
}
