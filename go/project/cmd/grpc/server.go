package main

import (
	pb "github.com/test/project/proto/gen/demo"
	"google.golang.org/grpc"
	"log"
	"net"
)

func StartGrpcServer() {
	listener, err := net.Listen("tcp", ":9001")
	if err != nil {
		log.Fatalln("Listen gRPC port failed: ", err)
	}

	//helloService := &DemoService{}
	helloService2 := &DemoService2{}
	server := grpc.NewServer()
	//pb.RegisterHelloServiceServer(server, helloService)
	pb.RegisterHelloServiceServer(server, helloService2)

	log.Println("Start gRPC Server on 0.0.0.0:9001")
	err = server.Serve(listener)
	if err != nil {
		log.Fatalln("Start gRPC Server failed: ", err)
	}
}
