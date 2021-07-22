package main

import (
	grpc_middleware "github.com/grpc-ecosystem/go-grpc-middleware"
	grpc_zap "github.com/grpc-ecosystem/go-grpc-middleware/logging/zap"
	"github.com/grpc-ecosystem/go-grpc-middleware/recovery"
	"github.com/test/project/demo/grpc/handler"
	user "github.com/test/project/demo/grpc/proto"
	"github.com/test/project/demo/grpc/util"
	"go.uber.org/zap"
	"google.golang.org/grpc"
	"log"
	"net"
	"os"
)

/*
GRPC_GO_LOG_SEVERITY_LEVEL=info;GRPC_TRACE=all;GRPC_VERBOSITY=DEBUG;GODEBUG=http2debug\=2
*/

const (
	port = ":50052"
)

func ZapInterceptor() *zap.Logger {
	logger, err := zap.NewDevelopment()
	if err != nil {
		log.Fatalf("failed to initialize zap logger: %v", err)
	}
	grpc_zap.ReplaceGrpcLoggerV2(logger)
	return logger
}

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
		grpc_zap.UnaryServerInterceptor(ZapInterceptor()),
	)
	s := grpc.NewServer(grpc.UnaryInterceptor(ints))
	log.Printf("env=%v", os.Getenv("GRPC_GO_LOG_SEVERITY_LEVEL"))
	user.RegisterGreeterServer(s, &handler.GreeterHandler{})
	if err := s.Serve(lis); err != nil {
		log.Fatalf("failed to serve: %v", err)
	}
}
