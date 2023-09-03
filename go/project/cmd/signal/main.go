package main

import (
	"fmt"
	"os"
	"os/signal"
	"syscall"
	"time"
)

func main() {
	s := make(chan os.Signal, 1)
	signal.Notify(s, syscall.SIGHUP, syscall.SIGINT, syscall.SIGTERM)

	for {
		time.Sleep(time.Second)
		fmt.Printf("signal, read\n")
		fmt.Printf("signal, v=%v\n", <-s)
	}
}
