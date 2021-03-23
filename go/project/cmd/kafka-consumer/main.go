package main

import (
	"fmt"
	"gopkg.in/confluentinc/confluent-kafka-go.v1/kafka"
)

const (
	Brokers = "localhost:9292"
	GroupID = "debug_test"
)

var (
	TopicTest = "test"
)

func main() {
	c, err := kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": Brokers,
		"group.id":          GroupID,
		"auto.offset.reset": "earliest",
	})

	if err != nil {
		panic(err)
	}

	c.SubscribeTopics([]string{TopicTest}, nil)
	tps, err := c.Assignment()
	fmt.Printf("tps=%v err=%v \n", tps, err)

	newTps := make([]kafka.TopicPartition, 0)
	for i := 0; i < 3; i++ {
		tp := kafka.TopicPartition{
			Topic:     &TopicTest,
			Partition: int32(i),
			Offset:    1616428800000,
		}
		newTps = append(newTps, tp)
	}

	offsets, err := c.OffsetsForTimes(newTps, 1000)
	fmt.Printf("offsets=%v err=%v \n", offsets, err)
	err = c.Assign(offsets)
	fmt.Printf("assign err=%v \n", err)

	tps, err = c.Assignment()
	fmt.Printf("tps=%v err=%v \n", tps, err)
	for {
		msg, err := c.ReadMessage(-1)
		if err == nil {
			fmt.Printf("type=%v time=%v", msg.TimestampType, msg.Timestamp)
			fmt.Printf("Message on %s: %s\n", msg.TopicPartition, string(msg.Value))
		} else {
			// The client will automatically try to recover from all errors.
			fmt.Printf("Consumer error: %v (%v)\n", err, msg)
		}
	}

	c.Close()
}
