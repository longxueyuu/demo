package main

import (
	"log"
	"net/http"
	_ "net/http/pprof"
	"runtime"
)

var (
	gb  []int64
	gb2 []int64
)

func main() {
	go func() {
		log.Fatal(http.ListenAndServe(":9898", nil))
	}()

	gb = getEnts()
	runtime.GC()

	gb2 = getEnts()
	runtime.GC()
	var t chan int
	<-t
}

func getEnts() []int64 {
	ents := make([]int64, 200*1024*1024, 200*1024*1024)
	log.Printf("len=%v cap=%v", len(ents), cap(ents))

	ents2 := ents[200*1024*1024-1024:]
	log.Printf("len=%v cap=%v", len(ents2), cap(ents2))
	return ents2
}
