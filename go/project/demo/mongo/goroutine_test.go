package main

import (
	"fmt"
	"testing"
	"time"
)

var sem = make(chan int, 5)

func TestShadowVariable(t *testing.T) {
	var queue = make(chan int, 100)

	reqs := []int{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
	for _, req := range reqs {
		queue <- req
	}
	close(queue)
	run(queue)
}

// 解决for循环多go程共享变量的一种方式
func run(queue chan int) {
	for req := range queue {
		req := req // 为该Go程创建 req 的新实例。
		sem <- 1
		go func() {
			time.Sleep(time.Second)
			fmt.Println(req)
			<-sem
		}()
	}

	time.Sleep(time.Minute)
}