package main

import (
	"context"
	"fmt"
	user "github.com/test/project/demo/grpc/proto"
	"google.golang.org/grpc"
	"log"
	"net"
	"time"
)

const (
	port = ":50051"
)

// server is used to implement helloworld.GreeterServer.
type server struct{}

// SayHello implements helloworld.GreeterServer
func (s *server) SayHello(ctx context.Context, in *user.HelloRequest) (*user.HelloReply, error) {
	log.Printf("Received: %v, meta=%v", in.Name, ctx.Value("meta"))
	time.Sleep(2 * time.Second)
	return &user.HelloReply{Message: "Hello " + in.Name, Kv: map[string]string{"name": "demo", "age": "30"}, Id: []string{"1", "2", "seq"}}, nil
}

func main() {
	fmt.Printf("hello, server\n")

	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}
	s := grpc.NewServer()

	user.RegisterGreeterServer(s, &server{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
