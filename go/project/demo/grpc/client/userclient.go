package main

import (
	"context"
	user "github.com/test/project/demo/grpc/proto"
	_ "github.com/test/project/demo/grpc/resolvers"
	"google.golang.org/grpc"
	"log"
	"os"
	"time"
)

const (
	address     = "demo://demo_authority/localhost:8888,localhost:50052"
	defaultName = "user_client"
)

func main() {
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
	ctx, cancel := context.WithTimeout(p, time.Minute)
	defer cancel()
	r, err := c.SayHello(ctx, &user.HelloRequest{Name: name})
	if err != nil {
		log.Fatalf("could not greet: %v, meta=%v", err, ctx.Value("meta"))
	}
	log.Printf("Greeting: %s", r)
}
