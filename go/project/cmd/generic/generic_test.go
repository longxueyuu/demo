package generic

import (
	"golang.org/x/exp/slices"
	"log"
	"reflect"
	"testing"
)

type Node[K any, V any] struct {
	k K
	v V
}

func (n Node[a, b]) Add(v b) []b {
	vs := make([]b, 0)
	return append(vs, v, n.v)
}

func eq[A, B comparable](a A, b B) bool {
	return reflect.ValueOf(a) == reflect.ValueOf(b)
}

func TestG(t *testing.T) {
	n1 := Node[string, string]{k: "s", v: "v"}
	n2 := Node[int, int]{k: 100, v: 200}
	eq := slices.EqualFunc([]int{100}, []Node[string, string]{n1}, eq[int, Node[string, string]])
	log.Printf("n1=%+v n2=%+v eq=%v", n1.Add("str"), n2.Add(1000), eq)
}
