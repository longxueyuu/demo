package main

import (
	"fmt"
	"os"
	"runtime/trace"
	"time"
)

//  trace
//	f, err := os.Create("./trace.out")
//	if err != nil {
//		panic(err)
//	}
//	defer f.Close()
//
//	err = trace.Start(f)
//	if err != nil {
//		panic(err)
//	}
//	defer trace.Stop()

//  app program here

// build: go build -gcflags -m .
// run: GODEBUG=schedtrace=1000 GOMAXPROCS=1 ./trace
func main() {
	//  trace
	f, err := os.Create("./trace.out")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	err = trace.Start(f)
	if err != nil {
		panic(err)
	}
	defer trace.Stop()

	for i := 0; i < 10; i++ {
		time.Sleep(time.Second)
		fmt.Println("hello world")
	}
}
