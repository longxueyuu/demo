package util

import (
	"log"
	"testing"
)

func TestChanClose(t *testing.T) {
	ch := make(chan bool, 1)
	ch <- false

	if <-ch {
		log.Printf("<-ch == %v", true)
	} else {
		log.Printf("<-ch == %v", false)
	}
	close(ch)
}
