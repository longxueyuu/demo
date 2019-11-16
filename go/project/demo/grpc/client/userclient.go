package main

import (
	"context"
	"fmt"
	user "github.com/test/project/demo/grpc/proto"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

const (
	address     = "localhost:50051"
	defaultName = "user client"
)

func main() {
	fmt.Printf("hello, client\n")

	// Set up a connection to the server.
	conn, err := grpc.Dial(address, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	defer conn.Close()
	c := user.NewGreeterClient(conn)

	// Contact the server and print out its response.
	name := defaultName
	if len(os.Args) > 1 {
		name = os.Args[1]
	}
	p := context.WithValue(context.Background(), "meta", "meta")
	ctx, cancel := context.WithTimeout(p, time.Second)
	defer cancel()
	r, err := c.SayHello(ctx, &user.HelloRequest{Name: name})
	if err != nil {
		log.Fatalf("could not greet: %v, meta=%v", err, ctx.Value("meta"))
	}
	log.Printf("Greeting: %s", r)
}
