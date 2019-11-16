package client

import (
	"fmt"
	"github.com/test/project/demo/gorpc/service"
	"log"
	"net/rpc"
	"testing"
)

const (
	serverAddr = "localhost:1234"
)

func TestArithClient(t *testing.T) {
	client, err := rpc.DialHTTP("tcp", serverAddr)
	if err != nil {
		log.Fatal("dialing:", err)
	}

	// Synchronous call
	args := &service.Args{9, 8}
	var reply int
	err = client.Call("Arith.Multiply", args, &reply)
	if err != nil {
		log.Fatal("arith error:", err)
	}
	fmt.Printf("Arith: %d*%d=%d \n", args.A, args.B, reply)

	// Asynchronous call
	quotient := new(service.Quotient)
	divCall := client.Go("Arith.Divide", args, quotient, nil)
	replyCall := <-divCall.Done // will be equal to divCall
	fmt.Printf("replyCall=%+v \n", replyCall.Reply)
	// check errors, print, etc.
}
