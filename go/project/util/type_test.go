package util

import (
	"fmt"
	"github.com/go-redis/redis"
	"github.com/gogo/protobuf/proto"
	"log"
	"reflect"
	"testing"
	"time"
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

func TestTypeElem(t *testing.T) {
	a := make(map[string]Reader, 0)

	x := interface{}(&a)

	v := reflect.ValueOf(x).Elem()
	log.Printf("v.kind=%v v.type=%v v.key.type=%v  v.element.kind=%v v.element.type=%v", v.Kind(), v.Type(), v.Type().Key(), v.Type().Elem().Kind(), v.Type().Elem())

	ifac := v.Type().Elem()
	i := reflect.New(ifac).Interface()
	j := reflect.Indirect(reflect.ValueOf(i))
	log.Printf("kind=%v v=%v et=%v", j.Kind(), j.Type(), j.IsNil())

	m := new(interface{})
	n := reflect.Indirect(reflect.ValueOf(m))
	log.Printf("kind=%v v=%v et=%v", n.Kind(), n.Type(), n.IsNil())

}

type Reader interface {
	Read()
}

type R struct {
	Name string
}

func (r *R) Read() {
	log.Printf("r struct method, %v", r.Name)
}

type Writer interface {
	Write()
}

type RW struct {
	Reader
	Writer
	Name string
}

func (rw RW) Read() {
	log.Printf("rw struct method, %v", rw.Name)
}

func TestEmbedding(t *testing.T) {
	var reader Reader
	reader = &RW{Reader: &R{Name: "R"}, Name: "RW"}
	reader.Read()
	// rw.Write()
}

type T struct {
	A int
	B string
	c string
}

func TestReflection(tt *testing.T) {
	t := T{23, "skidoo", "cc"}
	s := reflect.ValueOf(&t).Elem()
	typeOfT := s.Type()
	for i := 0; i < s.NumField(); i++ {
		f := s.Field(i)
		fmt.Printf("%d: %s %s %s = %v settable=%v\n", i,
			typeOfT.Field(i).Name, typeOfT.Field(i).Type, f.Type(), f.String(), f.CanSet())
	}
}

func setProtoRedisKV[V proto.Message](redisCli redis.Cmdable, k string, v V, expiration time.Duration) error {
	bs, err := proto.Marshal(v)
	if err != nil {
		log.Printf("setProtoRedisKV: k=%v err=%v", k, err)
		return err
	}

	err = redisCli.Set(k, bs, expiration).Err()
	if err != nil {
		log.Printf("setProtoRedisKV: k=%v v=%v err=%v", k, v, err)
		return err
	}

	return nil
}

func getProtoRedisKV[V proto.Message](redisCli redis.Cmdable, k string) (V, error) {
	var v V

	bs, err := redisCli.Get(k).Bytes()
	if err == redis.Nil {
		return v, nil
	}

	if err != nil {
		log.Printf("getProtoRedisKV: k=%v err=%v", k, err)
		return v, err
	}

	nv := reflect.New(reflect.TypeOf(v).Elem())
	v = nv.Interface().(V)
	err = proto.Unmarshal(bs, v)
	if err != nil {
		log.Printf("getProtoRedisKV:  k=%v nv=%v err=%v", k, string(bs), err)
		return v, err
	}

	return v, nil
}

type Ex[E any] struct {
	int64
	X E
}

func TestGenericStruct(t *testing.T) {
	x := Ex[bool]{X: true}
	getStruct(x)

	y := Ex[int64]{}
	getEmbed(y)
}

func getStruct[T ~struct {
	int64
	X E
}, E any](x T) {

}

func getEmbed[T Ex[X], X ~int64](x T) {

}
