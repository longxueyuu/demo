package main

import (
	"encoding/json"
	"fmt"
	"github.com/Shopify/sarama"
	"github.com/google/uuid"
	"log"
	"net/http"
	_ "net/http/pprof"
	"strconv"
	"strings"
	"time"
)

const (
	TopicTest = "test_sarama"
)

var (
	Brokers = []string{"localhost:9093", "localhost:9094", "localhost:9095"}
)

func NewKafkaAsyncProducer(brokers []string) (*sarama.AsyncProducer, error) {
	config := sarama.NewConfig()
	config.Producer.MaxMessageBytes = 10000000
	producer, err := sarama.NewAsyncProducer(brokers, config)
	log.Printf("NewKafkaAsyncProducer, producer=%v err=%v", producer, err)
	if err != nil {
		log.Printf("NewKafkaAsyncProducer, err=%v", err)
		panic(fmt.Sprintf("NewKafkaAsyncProducer, err=%v", err))
	}
	return &producer, nil
}

func SendAsyncMessage(producer *sarama.AsyncProducer, topic string, key string, data []byte) {
	log.Printf("SendAsyncMessage, pending, key=%v -------------", key)
	(*producer).Input() <- &sarama.ProducerMessage{
		Topic: topic,
		Key:   sarama.StringEncoder(key),
		Value: sarama.ByteEncoder(data),
	}
	log.Printf("SendAsyncMessage, sent, key=%v %v", key, string(data)[:10])
}

func main() {
	go func() {
		_ = http.ListenAndServe(":8989", nil)
	}()

	var i int64
	producer, _ := NewKafkaAsyncProducer(Brokers)
	var errCount = 1
	go func() {
		c := 0
		for {
			select {
			case err, ok := <-(*producer).Errors():
				if ok {
					log.Printf("SendAsyncMessage, err=%v", err)
					c++
					if c > errCount {
						time.Sleep(time.Hour)
					}
				}
			}
		}

	}()

	for {
		u, _ := uuid.NewUUID()
		data, _ := json.Marshal(map[string]string{
			"id":   u.String(),
			"name": strings.Repeat("testmsg", 5000),
		})
		time.Sleep(10 * time.Millisecond)
		i++
		key := strconv.FormatInt(i, 10)
		SendAsyncMessage(producer, TopicTest, key, data)
	}
}
