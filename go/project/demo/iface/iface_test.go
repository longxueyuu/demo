package iface

import (
	"bytes"
	"log"
	"net/http"
	"testing"
)

func TestIfaceNil(t *testing.T) {
	IfNil(nil)
}

func IfNil(v interface{}) {
	if v == nil {
		log.Printf("nil, v=%v", v)
	} else {
		log.Printf("not nil, v=%v", v)
	}
}

func TestBufNil(t *testing.T) {
	var buf = new(bytes.Buffer)
	_, err := http.NewRequest("httpMethod", "url", buf)
	if err != nil {
		log.Printf("FetchHTTPResponse: http.NewRequest, url=%v, err=%v", "url", err)
	}

}
