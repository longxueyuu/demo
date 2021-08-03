package main

import (
	"github.com/Shopify/sarama"
	"github.com/bsm/sarama-cluster"
	"log"
	"time"
)

const (
	TopicTest     = "test_sarama"
	ConsumerGroup = "consumer-group-test"
)

var (
	Brokers = []string{"localhost:9093", "localhost:9094", "localhost:9095"}
)

func NewKafkaConsumer(brokers []string, topics []string, group string) *cluster.Consumer {
	config := cluster.NewConfig()
	config.Consumer.Return.Errors = true
	config.Group.Return.Notifications = true

	config.Consumer.Offsets.Initial = sarama.OffsetNewest
	config.Consumer.Offsets.Retention = 0
	config.Consumer.Offsets.CommitInterval = time.Second

	consumer, err := cluster.NewConsumer(brokers, group, topics, config)
	if err != nil {
		log.Printf("NewKafkaConsumer: fail, err=%v", err)
		panic(err)
	}
	return consumer

}

func main() {
	consumer := NewKafkaConsumer(Brokers, []string{TopicTest}, ConsumerGroup)
	defer consumer.Close()
	for {
		select {
		case msg, ok := <-consumer.Messages():
			if ok {
				log.Printf("main: topic=%v partition=%v offset=%v key=%v value=%v", msg.Topic, msg.Partition, msg.Offset, string(msg.Key), string(msg.Value))
				time.Sleep(10 * time.Millisecond)
				consumer.MarkOffset(msg, "")
			}
		case err, ok := <-consumer.Errors():
			if ok {
				log.Printf("main: consumer, err=%v", err)
			}
		case ntf, ok := <-consumer.Notifications():
			if ok {
				log.Printf("main: consumer, ntf=%v", ntf)
			}
		}
	}
}
