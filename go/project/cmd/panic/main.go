package main

import (
	"fmt"
	"math/rand"
	"os"
	"runtime"
)

func main() {
	sigs := make(chan os.Signal, 0)
	go F(func() {
		fmt.Printf("panic\t")
		panic("test")
	})

	go F(func() {
		for {
			// select {
			// case x := <-sigs:
			// 	fmt.Println(fmt.Sprintf("tail, x=%v", x))
			// 	break
			// }
			rand.Int31n(10000)
			// fmt.Println(fmt.Sprintf("tail, x=%v", x))
		}
	})

	fmt.Println("hanging .....")
	<-sigs
}

func F(f func()) {
	defer func() {
		if err := recover(); err != nil {
			var buf [1 << 10]byte
			runtime.Stack(buf[:], true)
			fmt.Println("err=", err, "stack\n", string(buf[:]))
		}
	}()
	f()
}
