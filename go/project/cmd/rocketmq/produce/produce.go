package main

import (
	"context"
	"encoding/json"
	"errors"
	"github.com/test/project/cmd/rocketmq/common"
	"log"
	"net/http"
	"os"
	"strings"
	"time"

	rocketmq "github.com/apache/rocketmq-clients/golang/v5"
	"github.com/apache/rocketmq-clients/golang/v5/credentials"
	_ "net/http/pprof"
)

func main() {
	go func() {
		log.Fatal(http.ListenAndServe(":9292", nil))
	}()
	go produceV2()
	produce(common.TopicDemo)
}

func produce(topic string) {
	p, err := NewRocketMQProducer(common.EndPoints, "~/demo/go/project/cmd/rocketmq")
	if err != nil {
		log.Printf("new producer, err=%v", err)
		panic(err)
	}

	n := 10000000
	name := topic
	for i := 0; i < n; i++ {
		time.Sleep(100 * time.Millisecond)
		key := common.UUID()
		event := map[string]interface{}{
			"key":  key,
			"i":    i,
			"name": name,
			"ts":   time.Now().Format(time.DateTime),
		}
		dts := time.Now().Add(10 * time.Second)
		r, err := sendDelayMsg(context.Background(), p, topic, event, dts, "", []string{key})
		log.Printf("send delay msg, i=%v r=%v err=%v", i, r, err)

	}
}

func NewRocketMQProducer(endPoint []string, exclogPath string) (rocketmq.Producer, error) {
	os.Setenv("mq.consoleAppender.enabled", "true")
	os.Setenv("rocketmq.client.logLevel", "INFO")
	os.Setenv("rocketmq.client.logRoot", exclogPath)
	rocketmq.ResetLogger()
	p, err := rocketmq.NewProducer(&rocketmq.Config{
		Endpoint:    strings.Join(endPoint, ";"),
		Credentials: &credentials.SessionCredentials{},
	})
	if err != nil {
		return nil, err
	}
	err = p.Start()
	if err != nil {
		return nil, err
	}
	return p, err
}

func sendDelayMsg(ctx context.Context, p rocketmq.Producer, topic string, event interface{}, delayTs time.Time, tag string, keys []string) (*rocketmq.SendReceipt, error) {
	msgBody, err := json.Marshal(event)
	if err != nil {
		return nil, err
	}

	msg := &rocketmq.Message{
		Topic: topic,
		Body:  msgBody,
	}
	msg.SetDelayTimestamp(delayTs)
	msg.SetTag(tag)
	msg.SetKeys(keys...)
	res, err := p.Send(ctx, msg)
	if err != nil {
		return nil, err
	}

	for _, v := range res {
		return v, nil
	}
	return nil, errors.New("receipt not found")
}
