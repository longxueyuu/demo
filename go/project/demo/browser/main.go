package main

import (
	"github.com/pkg/browser"
	"log"
)

func main() {
	const url = "http://golang.org/"
	err := browser.OpenURL(url)
	log.Fatal(err)
}
