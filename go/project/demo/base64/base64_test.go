package base64

import (
	"encoding/base64"
	"fmt"
	"log"
	"testing"
)

func TestDecode(t *testing.T) {
	s := "abc"
	b, err := base64.StdEncoding.DecodeString(s)
	if err != nil {
		log.Printf("decode err, err=%v", err)
	}
	fmt.Println(string(b))
}
