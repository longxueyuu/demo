package main

import (
	"context"
	"encoding/json"
	"google.golang.org/genproto/googleapis/api/httpbody"
	"google.golang.org/grpc"
	"google.golang.org/grpc/metadata"

	pb "github.com/test/project/proto/gen/demo"
)

var (
	_ pb.HelloServiceServer = (*DemoService2)(nil)
)

type DemoService2 struct {
}

func (h *DemoService2) GetUserProfile(ctx context.Context, req *pb.UserProfileReq) (*httpbody.HttpBody, error) {
	_ = grpc.SetHeader(ctx, metadata.Pairs("x-http-code", "201"))
	_ = grpc.SendHeader(ctx, metadata.New(map[string]string{
		"x-custom-header1": "value",
		"x-custom-header2": "value2",
	}))

	data := map[string]string{
		"uid":  req.GetUid(),
		"name": "svc2: GetUserProfile",
	}
	b, _ := json.Marshal(data)

	return &httpbody.HttpBody{
		ContentType: "application/json",
		Data:        b,
	}, nil
}

func (h *DemoService2) Hello(ctx context.Context, req *pb.HelloReq) (*pb.HelloResp, error) {
	helloMessage := "svc2: Hello " + req.GetMessage()

	response := pb.HelloResp{Result: helloMessage}

	return &response, nil
}

func (h *DemoService2) Send(ctx context.Context, req *pb.SendReq) (*pb.SendResp, error) {
	resp := &pb.SendResp{
		User: &pb.User{
			Uid:  req.User.Uid,
			Name: "svc2: " + req.User.Name,
		},
		Op: req.Op,
	}

	return resp, nil
}
