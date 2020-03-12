package main

import "fmt"

// build: go build -gcflags -m .
// run: GODEBUG=schedtrace=1000 GOMAXPROCS=1 ./preemptive
func main() {
	done := false

	go func() {
		done = true
		fmt.Println("unblocked")
	}()

	for !done {
		_ = inlineFunc(1, 2)
	}
	fmt.Println("done!")
}

func inlineFunc(a, b int) int {
	//fmt.Println(a, b) // prevent from inlining
	return a + b
}
