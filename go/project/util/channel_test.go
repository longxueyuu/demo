package util

import (
	"fmt"
	"testing"
)

func TestNilChannel(t *testing.T) {
	var ch chan int
	fmt.Println(ch)
	//v, ok := <-ch
	//fmt.Println(v, ok)
}

func TestCloseChannel(t *testing.T) {
	var ch = make(chan int)
	fmt.Println(ch)

	close(ch)

	v, ok := <-ch
	fmt.Println(v, ok)
}

func TestForRangeChannel(t *testing.T) {
	var ch = make(chan int, 10)

	ch <- 1
	ch <- 2

	fmt.Println("start")
	//for v := range ch {
	//	fmt.Println(v)
	//}
	fmt.Println("end")
}
