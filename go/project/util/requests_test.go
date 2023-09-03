package util

import (
	"context"
	"github.com/carlmjohnson/requests"
	"log"
	"testing"
)

func TestRequestsGet(t *testing.T) {
	data := map[string]interface{}{}
	err := requests.URL("http://www.baidu.com").
		ToJSON(&data).Fetch(context.Background())
	if err != nil {
		panic(err)
	}
	log.Printf("data=%v", data)
}
