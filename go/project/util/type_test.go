package util

import (
	"log"
	"reflect"
	"testing"
)

type Type int

type TypeCall struct {
}

func (tc *TypeCall) Call() reflect.Value {
	ct := reflect.ValueOf("calltest")
	log.Printf("ct: %v %v %v", ct, ct.Type(), ct.Type().String())
	return ct
}

func TestType(t *testing.T) {
	ids := []int{1, 2, 3}
	ids = append(ids, 4)
	//Get(append(interface{}{}(nil), ids...))

	x := reflect.TypeOf((*reflect.Value)(nil)).Elem()
	log.Printf("%v %v %v", x, x.Name(), x.String())

	argv := make([]reflect.Value, 0)
	ret := reflect.ValueOf(&TypeCall{}).Method(0).Call(argv)
	v := ret[0]
	if v.Type() == x {
		y := v.Interface().(reflect.Value)
		log.Printf("y: %v %v %v", y, y.Type(), y.Type().String())
	}
	log.Printf("v: %v %v %v", v, v.Type(), v.Type().String())
}

func Get(ids ...interface{}) {
	log.Printf("ids=%v", ids)
}
