package util

import (
	"fmt"
	"github.com/huandu/go-clone"
	"log"
	"reflect"
	"testing"
	"unsafe"
)

type Person struct {
	ID   int64
	Name string
}

type User struct {
	Person
	UID string

	hide *string
	uexp string
	Desc interface{}
}

type Admin struct {
	*Person
	UID string
}

func (p *Person) GetName() string {
	p.Name = "changed"
	return p.Name
}

func TestUser(t *testing.T) {
	p1 := Person{Name: "user"}
	name := p1.GetName()
	log.Printf("name=%v", name)

	p2 := &Person{}
	p2.GetName()

	user := User{UID: "user1", Person: Person{Name: "user"}}
	un := user.GetName()
	log.Printf("user=%+v, person=%v un=%v", user, user.Person, un)

	admin := Admin{UID: "admin", Person: &Person{Name: "admin"}}
	an := admin.GetName()
	log.Printf("admin=%+v person=%v an=%v", admin, admin.Person, an)
}

func TestHideClone(t *testing.T) {
	s := "hided"
	u := &User{
		Person: Person{},
		UID:    "001",
		hide:   &s,
		uexp:   "test",
		Desc:   "desc",
	}

	//reflect unexported
	hide := reflect.ValueOf(u).Elem().Field(2).Elem()

	f := reflect.ValueOf(u).Elem().Field(3).Addr()
	old := f.Elem().String()
	uexpPtr := (*string)(unsafe.Pointer(f.Pointer()))
	*uexpPtr = "unexport mod success"
	log.Printf("mod unexport field: old=%v new=%v", old, *uexpPtr)

	ucop := clone.Clone(u)
	log.Printf("%+v canSet=%v readFromReflect=%v ucopy=%v", u, hide.CanSet(), hide.String(), ucop)

	newS := "abcd"
	bp := unsafe.StringData(newS)
	bpe := (*byte)(unsafe.Pointer((uintptr)(unsafe.Pointer(bp)) + uintptr(3)*unsafe.Sizeof(newS[0])))
	bs := unsafe.Slice(bp, len(newS))
	//bs[0] = 80 // modify this will call fatal panic
	var m = map[string]string{
		"t":  "v",
		"tv": "v",
	}

	mp := unsafe.Sizeof(m)
	mcountp := (*int)(unsafe.Pointer(&m))
	rp := (*int)(unsafe.Pointer(uintptr(*mcountp)))

	log.Printf("newS=%v bs=%v bp=%v bpe=%v mp=%v mcountp=%v maplen=%v", newS, bs, *bp, *bpe, mp, *mcountp, *rp)

	sl := make([]int, 3, 5)
	fmt.Println("a>", len(sl), cap(sl)) // a> 3 5
	modifySlice(sl)
	fmt.Println("c>", len(sl), cap(sl)) // c> 3 5
	log.Printf("slicesize=%v", unsafe.Sizeof(sl))
}

func modifySlice(s []int) {
	reflect.ValueOf(&s).Elem().SetLen(4)
	reflect.ValueOf(&s).Elem().SetCap(4)
	fmt.Println("b>", len(s), cap(s)) // b> 4 4
}
