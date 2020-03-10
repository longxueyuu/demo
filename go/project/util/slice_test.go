package util

import (
	"log"
	"sync"
	"testing"
	"time"
)

func TestConcurrentWriteSlice(t *testing.T) {
	for i := 0; i < 100; i++ {
		testConcurrentWriteSlice()
	}
}

func testConcurrentWriteSlice() {
	var strs []string

	var wg sync.WaitGroup
	for x := 0; x < 1000; x++ {
		wg.Add(1)
		go func() {
			defer func() { wg.Done() }()
			time.Sleep(2 * time.Second)
			for i := 65; i < 91; i++ {
				strs = append(strs, string(i))
			}
		}()
	}
	wg.Wait()

	l := len(strs)
	log.Printf("strs len: %v f=%v l=%v", l, strs[0], strs[l-1])
	for i := 0; i < l; i++ {
		log.Printf("strs len=%v i=%v first=%v last=%v &current[i]=%v", l, i, strs[0], strs[l-1], &strs[i])
		log.Printf("strs len=%v i=%v first=%v last=%v current=%v", l, i, strs[0], strs[l-1], strs[i])
	}
}
