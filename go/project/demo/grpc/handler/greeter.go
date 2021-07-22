package handler

import (
	"context"
	user "github.com/test/project/demo/grpc/proto"
	"log"
	"time"
)

// handler is used to implement helloworld.GreeterServer.
type GreeterHandler struct{}

var (
	_ user.GreeterServer = (*GreeterHandler)(nil)
)

// SayHello implements helloworld.GreeterServer
func (s *GreeterHandler) SayHello(ctx context.Context, in *user.HelloRequest) (*user.HelloReply, error) {
	time.Sleep(time.Second * 5)
	log.Printf("Received: %v, meta=%v", in.Name, ctx.Value("meta"))
	return &user.HelloReply{Message: " Hello " + in.Name, Kv: map[string]string{"name": "demo", "age": "30"}, Id: []string{"1", "2", "seq"}}, nil
}
