package util

import (
	"log"
	"math"
	"strconv"
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
				strs = append(strs, strconv.Itoa(i))
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

func TestSlice(t *testing.T) {
	ss := []string{"string"}

	ss2 := ss[:]
	log.Printf("ss2=%v", ss2)

	x := math.MaxInt64

	y := int64(float64(x))

	var p []int
	q := (*int)(nil)
	log.Printf("x=%v y=%v p=%p q=%p", x, y, p, q)
}
