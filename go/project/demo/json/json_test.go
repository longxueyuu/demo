package json

import (
	"encoding/json"
	"fmt"
	"log"
	"testing"
)

type Item struct {
	ID   string `json:"id"`
	Name string `json:"name"`
}

func TestJSONUnMarshall(t *testing.T) {
	items := make([]Item, 0)
	s := "[{\"id\": \"1\", \"name\": \"aa\"}, {\"id\": \"2\", \"name\": \"bb\"}]"
	err := json.Unmarshal([]byte(s), &items)
	if err != nil {
		fmt.Println("err: ", err)
	} else {
		for i, item := range items {
			fmt.Println(i, item.ID, item.Name)
		}
	}

	m := map[string][]*Item{
		"item1": {{ID: "a", Name: "b"}, {ID: "a", Name: "b"}},
		"item2": {{ID: "a", Name: "b"}, {ID: "a", Name: "b"}},
	}
	b, _ := json.Marshal(m)
	fmt.Println(string(b))

	v := make(map[string]interface{})
	err = json.Unmarshal(b, &v)
	if err != nil {
		log.Printf("unmarshall: fail, %v", err)
		return
	}
	log.Printf("unmarshall: %v", v)
}

func TestJSONMarshallNil(t *testing.T) {
	b, err := json.Marshal(nil)
	if err != nil {
		log.Printf("err: %v", err)
		return
	}
	log.Printf("unmarshall: b=%v len=%v", string(b), len(string(b)))
}

func TestJSONZeroMap(t *testing.T) {
	var st struct {
		m map[string]interface{} `json:"a"`
	}
	b, err := json.Marshal(st)
	if err != nil {
		log.Printf("err: %v", err)
		return
	}
	log.Printf("unmarshall: b=%v len=%v", string(b), len(string(b)))

}
