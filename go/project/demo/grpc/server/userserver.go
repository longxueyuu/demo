package main

import (
	grpc_middleware "github.com/grpc-ecosystem/go-grpc-middleware"
	"github.com/grpc-ecosystem/go-grpc-middleware/recovery"
	"github.com/test/project/demo/grpc/handler"
	user "github.com/test/project/demo/grpc/proto"
	"github.com/test/project/demo/grpc/util"
	"google.golang.org/grpc"
	"log"
	"net"
)

const (
	port = ":50052"
)

func main() {
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

	user.RegisterGreeterServer(s, &handler.GreeterHandler{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
