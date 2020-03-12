package main

import (
	"github.com/test/project/cmd/demoapi/demo"
	"net/http"
)

func init() {
	http.HandleFunc("/api/demo", demo.HandleDemo)
}
