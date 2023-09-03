package main

import (
	"context"
	"fmt"
	"os"
	"strconv"
	"time"

	"github.com/apache/rocketmq-client-go/v2"
	"github.com/apache/rocketmq-client-go/v2/primitive"
	"github.com/apache/rocketmq-client-go/v2/producer"
)

// Package main implements a simple producer to send message.
func produceV2() {
	p, _ := rocketmq.NewProducer(
		producer.WithNsResolver(primitive.NewPassthroughResolver([]string{"127.0.0.1:9876"})),
		producer.WithRetry(2),
	)
	err := p.Start()
	if err != nil {
		fmt.Printf("start producer, error=%s", err.Error())
		os.Exit(1)
	}
	topic := "test_topic"

	n := 10000000
	for i := 0; i < n; i++ {
		time.Sleep(100 * time.Millisecond)
		msg := &primitive.Message{
			Topic: topic,
			Body:  []byte("Hello RocketMQ Go Client! " + strconv.Itoa(i)),
		}
		res, err := p.SendSync(context.Background(), msg)

		if err != nil {
			fmt.Printf("send message, error=%s\n", err)
		} else {
			fmt.Printf("send message success, result=%s\n", res.String())
		}
	}
	err = p.Shutdown()
	if err != nil {
		fmt.Printf("shutdown producer, error=%s", err.Error())
	}
}
