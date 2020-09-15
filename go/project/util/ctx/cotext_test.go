package ctx

import (
	"context"
	"log"
	"testing"
	"time"
)

func TestContextCancel(t *testing.T) {
	baseCtx, baseCancel := context.WithCancel(context.Background())
	ctx, _ := context.WithTimeout(baseCtx, time.Second*5)

	baseCancel()
	log.Printf("err=%v", ctx.Err())
}

func TestContextTimeout(t *testing.T) {
	baseCtx, _ := context.WithCancel(context.Background())
	ctx, _ := context.WithTimeout(baseCtx, time.Second*5)

	time.Sleep(time.Second * 6)

	log.Printf("err=%v", ctx.Err())
}

func TestCtx(t *testing.T) {
	c, cancel := context.WithTimeout(context.Background(), time.Second*5)
	go func() {
		select {
		case <-c.Done():
			log.Printf("done")
		}
	}()
	time.Sleep(time.Second)
	cancel()
	time.Sleep(time.Second * 5)
}
