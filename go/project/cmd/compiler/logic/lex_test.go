package main

import (
	"log"
	"testing"
)

func TestNewTokenizer(t *testing.T) {
	s := "100 and 200 or 20"
	tz := NewTokenizer(s)
	for tz.HasNext() {
		log.Printf("%v\n", tz.Next().Value)
	}
}
