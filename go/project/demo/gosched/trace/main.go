package main

import (
	"context"
	"fmt"
	"github.com/carlmjohnson/requests"
	"os"
	"runtime/trace"
	"sync"
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

	wg := &sync.WaitGroup{}
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func() {
			runTask()
			wg.Done()
		}()
	}
	wg.Wait()
}

// use pprof trace task/region in application code
func runTask() {
	ctx, task := trace.NewTask(context.Background(), "main-task")
	r := trace.StartRegion(ctx, "prepare")
	m := make(map[string]interface{})
	requests.URL("").ToJSON(&m).Fetch(ctx)
	lock := sync.Mutex{}
	r.End()

	ch := make(chan int, 0)
	go func() {
		defer task.End()
		lock.Lock()
		defer lock.Unlock()

		category := "do-work"
		r2 := trace.StartRegion(ctx, category)
		work(ctx, category)
		r2.End()
		close(ch)
	}()

	<-ch
}

func work(ctx context.Context, category string) {
	for i := 0; i < 10; i++ {
		trace.Logf(ctx, category, "work-progress-%v", i)
		time.Sleep(50 * time.Millisecond)
		fmt.Println("hello world")
	}
}
