package generic

import (
	"golang.org/x/exp/slices"
	"log"
	"reflect"
	"sync"
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

	slices.SortFunc(([]int)(nil), func(a, b int) bool { return false })
	log.Printf("n1=%+v n2=%+v eq=%v", n1.Add("str"), n2.Add(1000), eq)
}

type List[T any] []T

type HashMap[K comparable, V any] map[K]V

type GenericSyncMap[K comparable, V any] struct {
	s sync.Map
}

func (g *GenericSyncMap[K, V]) Load(k K) (V, bool) {
	v, ok := g.s.Load(k)
	if ok {
		return v.(V), ok
	}

	var zero V
	return zero, false
}

func (g *GenericSyncMap[K, V]) Store(k K, v V) {
	g.s.Store(k, v)
}

func (g *GenericSyncMap[K, V]) Range(f func(k K, v V) bool) {
	g.s.Range(func(key, value any) bool {
		return f(key.(K), value.(V))
	})
}

func TestGenericMap(t *testing.T) {
	list := make(List[string], 0)
	list = append(list, "a", "bb")

	list2 := make(List[string], 0)
	list2 = append(list2, "Name", "Gender")
	log.Printf("list=%v", list2)

	m := make(HashMap[string, int64])
	m["key"] = 100
	m["key2"] = 1000
	log.Printf("m=%v", m)

	g := GenericSyncMap[string, List[string]]{}
	g.Store("list", list)
	g.Store("list2", list2)
	//g.Store("list3", nil)
	x, _ := g.Load("list3")
	log.Printf("list3=%v", x)

	g.Range(func(k string, v List[string]) bool {
		log.Printf("sync range: k=%v v=%v", k, v)
		return true
	})

}
