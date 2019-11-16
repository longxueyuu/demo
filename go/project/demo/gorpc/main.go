package main

import "github.com/test/project/demo/gorpc/server"

func main() {

	server.StartArithService()

	select {}
}
