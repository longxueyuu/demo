package main

import (
	"flag"
	"github.com/gorilla/websocket"
	"log"
	"net/http"
	"time"
)

var addr = flag.String("addr", "localhost:8282", "http service address")

var upgrader = websocket.Upgrader{} // use default options

func echo(w http.ResponseWriter, r *http.Request) {
	c, err := upgrader.Upgrade(w, r, nil)
	if err != nil {
		log.Print("upgrade:", err)
		return
	}

	defer c.Close()
	for {
		c.SetReadDeadline(time.Now().Add(time.Second * 1))
		_, message, err := c.ReadMessage()
		if err != nil {
			log.Println("read:", err)
			time.Sleep(time.Second)
			c.SetReadDeadline(time.Now().Add(time.Second * 5))
			continue
		}
		log.Printf("recv: %s", message)
	}
}

func main() {
	flag.Parse()
	log.SetFlags(0)
	http.HandleFunc("/echo", echo)
	log.Fatal(http.ListenAndServe(*addr, nil))

}
