package main

import (
	"math/rand"
	"os"
	"runtime/trace"
	"sync"
)

// build asm: go tool compile -S ./main.go        # or: go build -gcflags -S ./main.go
func main() {
	//  trace
	f, err := os.Create("trace.out")
	if err != nil {
		panic(err)
	}
	defer f.Close()

	err = trace.Start(f)
	if err != nil {
		panic(err)
	}
	defer trace.Stop()

	var total int
	var wg sync.WaitGroup

	for i := gen(0); i < 20; i++ {
		wg.Add(1)
		go func(g gen) {
			for j := 0; j < 1e7; j++ {
				total += g.readNumber()
			}
			wg.Done()
		}(i)
	}

	wg.Wait()
}

var generators [20]*rand.Rand

func init() {
	for i := int64(0); i < 20; i++ {
		generators[i] = rand.New(rand.NewSource(i).(rand.Source64))
	}
}

type gen int

//go:noinline
func (g gen) readNumber() int {
	return generators[int(g)].Intn(10)
}
