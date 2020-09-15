package util

import (
	"log"
	"testing"
)

type Person struct {
	ID   int64
	Name string
}

type User struct {
	Person
	UID string
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
