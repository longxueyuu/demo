package server

import (
	"log"
	"net"
	"net/http"
	"net/rpc"

	"github.com/test/project/demo/gorpc/service"
)

func StartArithService() {
	arith := new(service.Arith)
	_ = rpc.Register(arith)
	rpc.HandleHTTP()
	l, e := net.Listen("tcp", ":1234")
	if e != nil {
		log.Fatal("listen error:", e)
	}

	http.Serve(l, nil)
}
