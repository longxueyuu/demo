package main

import (
	"fmt"
	"github.com/test/project/cmd/gw/server"
)

func main() {
	server.StartGwServer()
	fmt.Println("hello grpc gateway")
}
