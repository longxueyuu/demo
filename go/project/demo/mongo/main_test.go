package main

import (
	"bufio"
	"encoding/json"
	"fmt"
	"reflect"
	"strconv"
	"testing"
)

type Person struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

func (p Person) MarshalJSON() ([]byte, error) {
	strFormat := `{"person": "%s"}`
	return []byte(fmt.Sprintf(strFormat, p.Name+strconv.Itoa(p.Age))), nil
}

func TestGet(t *testing.T) {

	n := new(int)
	*n = 10
	b, _ := json.Marshal(n)
	fmt.Println(string(b))
	fmt.Printf("n=%+v\n", *n)

}

func TestMapKey(t *testing.T) {
	p1 := Person{Name: "leo", Age: 39}
	p2 := Person{Name: "leo", Age: 39}

	persons := make([]Person, 0)
	persons = append(persons, p1)
	persons = append(persons, p2)
	b, err := json.Marshal(persons)
	fmt.Println("persons: ", string(b), err)

	ma := make(map[Person]string)
	ma[p1] = "leo"
	ma[p2] = "leo"

	b, err = json.Marshal(ma)
	fmt.Println("a with struct key: ", string(b), ma, err, len(ma))

	mb := make(map[*Person]string)
	mb[&p1] = "leo"
	mb[&p2] = "leo"

	b, err = json.Marshal(mb)
	fmt.Println("a with pointer key: ", string(b), mb, err, len(mb))
}

type Run interface {
	Run()
}

func (p Person) Run() {

}
func TestInterfaceType(t *testing.T) {
	var u Run = Person{}
	if u == nil {
		fmt.Println("u is nil", u)
	} else {
		fmt.Println("u is not nil", u)
	}
	fmt.Println(reflect.TypeOf(u), reflect.ValueOf(u).Interface().(Run))

	var p *Person = nil
	if p == nil {
		fmt.Println("p is nil", p)
	} else {
		fmt.Println("p is not nil", p)
	}

	var run Run = p
	if run == nil {
		fmt.Println("run is nil", run)
	} else {
		fmt.Printf("run is not nil: %#v \n", run)
	}

}

func TestGoto(t *testing.T) {

	fmt.Println("goto test")
	a := 1
	if a < 10 {
		goto end
	} else {
		fmt.Println("goto not executed")
	}

end:
	fmt.Println("goto here")
}

func TestNilMap(t *testing.T) {

	var v map[string]interface{}
	rv := reflect.ValueOf(&v)
	v = *(rv.Interface().(*map[string]interface{}))

	_ = json.Unmarshal([]byte("{\"name\": \"leo\"}"), &v)

	// panic: assignment to entry in nil map
	//v["name"] = "leo"

	fmt.Println("value", v)
}

func TestSwitch(t *testing.T) {

	i := 5
	switch {
	case i <= 10:
		fmt.Println("i<=10")
		fallthrough
	case i < 20:
		fmt.Println("i<20")
		fallthrough
	default:
		fmt.Println("i<30")
	}
}

func TestAddressOfStruct(t *testing.T) {
	p := Person{Name: "trump", Age: 49}

	pp := &p

	pp.Name = "hillary"
	fmt.Printf("p=%v, pp=%v\n", p, pp)
}

func TestCompositeLiterals(t *testing.T) {
	a := [...]string{0: "no error", 2: "Eio", 3: "invalid argument"}
	s := []string{5: "no error", 7: "Eio", 9: "invalid argument"}
	m := map[int]string{1: "no error", 2: "Eio", 3: "invalid argument"}
	fmt.Println(a, len(a), s, len(s), m)
}

type RW struct {
	*bufio.Reader
	*bufio.Writer
}

func TestStructEmbedding(t *testing.T) {
	rw := &RW{Reader: nil, Writer: nil}
	_ = rw
	// now ReadWriter implemented io.Reader & io.Writer
	//_, _ = rw.Read([]byte{})
	//
	//var i io.Reader = rw
	//_, _ = i.Read([]byte{})
}
