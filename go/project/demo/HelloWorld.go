package main

import (
	"log"
	"path"
	"runtime"
	"time"
)

func main() {
	log.Printf("hello, world")

	t, _ := time.ParseInLocation("2006-01-02 15:04:05", "2016-06-13 15:34:39", time.Local)
	// 整点（向下取整）
	log.Println(t.Truncate(1 * time.Hour))
	// 整点（最接近）
	log.Println(t.Round(1 * time.Hour))

	// 整分（向下取整）
	log.Println(t.Truncate(1 * time.Minute))
	// 整分（最接近）
	log.Println(t.Round(1 * time.Minute))

	t2, _ := time.ParseInLocation("2006-01-02 15:04:05", t.Format("2006-01-02 15:00:00"), time.Local)
	log.Println(t2)

	dir, file := path.Split("")
	log.Printf("dir=%s, file=%s", dir, file)

	log.Printf("NumCPU=%v", runtime.NumCPU())
}
