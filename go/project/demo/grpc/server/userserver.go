package main

import (
	"context"
	"fmt"
	grpc_middleware "github.com/grpc-ecosystem/go-grpc-middleware"
	"github.com/grpc-ecosystem/go-grpc-middleware/recovery"
	user "github.com/test/project/demo/grpc/proto"
	"github.com/test/project/demo/grpc/util"
	"google.golang.org/grpc"
	"log"
	"net"
)

const (
	port = ":50052"
)

// handler is used to implement helloworld.GreeterServer.
type handler struct{}

var (
	_ user.GreeterServer = (*handler)(nil)
)

// SayHello implements helloworld.GreeterServer
func (s *handler) SayHello(ctx context.Context, in *user.HelloRequest) (*user.HelloReply, error) {
	log.Printf("Received: %v, meta=%v", in.Name, ctx.Value("meta"))
	return &user.HelloReply{Message: port + " Hello " + in.Name, Kv: map[string]string{"name": "demo", "age": "30"}, Id: []string{"1", "2", "seq"}}, nil
}

func main() {
	fmt.Printf("hello, handler\n")

	lis, err := net.Listen("tcp", port)
	if err != nil {
		log.Fatalf("failed to listen: %v", err)
	}

	opts := []grpc_recovery.Option{
		grpc_recovery.WithRecoveryHandler(util.CustomRecoveryHandlerFunc),
	}
	ints := grpc_middleware.ChainUnaryServer(
		grpc_recovery.UnaryServerInterceptor(opts...),
	)
	s := grpc.NewServer(grpc.UnaryInterceptor(ints))

	user.RegisterGreeterServer(s, &handler{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
