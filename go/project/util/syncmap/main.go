package main

import (
	"math/rand"
	"sync"
)

// A Map must not be copied after first use.
func main() {
	var m sync.Map

	for i := 0; i < 64; i++ {
		key := rand.Intn(128)
		m.Store(key, key)
	}
	n := m
	go func() {
		for {
			key := rand.Intn(128)
			m.Store(key, key)
		}
	}()
	for {
		n.Range(func(key, value interface{}) bool {
			return key == value
		})
	}
}
