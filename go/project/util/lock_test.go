package util

import (
	"log"
	"net/http"
	_ "net/http/pprof"
	"runtime"
	"sync"
	"testing"
)

func TestRWLock(t *testing.T) {
	rate := 1
	runtime.SetBlockProfileRate(rate)
	runtime.SetMutexProfileFraction(rate)
	go func() {
		log.Fatal(http.ListenAndServe(":6060", nil))
	}()

	mu := &sync.Mutex{}
	l := &sync.RWMutex{}

	i := 0
	for i < 10 {
		go mutex(mu, 0)
		go rwlock(l, 0)
		i++
	}

	ch := make(chan int)
	ch <- 1
}

func mutex(l *sync.Mutex, level int) {
	log.Printf("rwlock: level=%v", level)
	if level > 100 {
		return
	}

	l.Lock()
	l.Unlock()
	mutex(l, level+1)
}

func rwlock(l *sync.RWMutex, level int) {
	log.Printf("rwlock: level=%v", level)
	if level > 100 {
		return
	}

	l.RLock()
	l.RUnlock()
	rwlock(l, level+1)
}
