package main

import (
	"log"
	"sync"
	"time"
)

// go run -race cmd/gorace/main.go
func main() {
	println("hello world!")

	x := make(map[string]string)
	log.Printf("parameter: m=%p", &x)
	addrmap(x)

	var c int64
	log.Printf("x is nil = %v", x == nil)
	var lock sync.Mutex
	go func() {
		for {
			y := make(map[string]string)
			y["x"] = "zero"

			lock.Lock()
			x = y
			lock.Unlock()
		}
	}()

	for i := 0; i < 10000; i++ {
		go func() {
			defer func() {
				//if err := recover(); err != nil {
				//	log.Printf("recover: c=%v stack=%v", c, string(debug.Stack()))
				//}
				log.Printf("read exit: c=%v", c)
			}()
			for {
				//x["v"] = "x"

				if _, ok := x["x"]; ok {
					c++
				}
				//c += amap(x)
			}
		}()
	}

	for {
		time.Sleep(time.Minute)
		println(c)
	}

	t := make(chan int)
	t <- 1
}

func amap(m map[string]string) int64 {
	if _, ok := m["x"]; ok {
		return 1
	}

	return 0
}

func addrmap(m map[string]string) {
	log.Printf("parameter: m=%p", &m)
}
