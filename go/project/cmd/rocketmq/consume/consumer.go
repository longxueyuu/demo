package main

import (
	"context"
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
		log.Fatal(http.ListenAndServe(":9191", nil))
	}()
	go consumeV2()
	consume(common.TopicDemo, common.ConsumeGroupDemo)
}

func consume(topic, consumeGroup string) {
	c, closeFn, err := NewConsumer(common.EndPoints, consumeGroup, topic, common.MaxMessageNum,
		processMsg, "~/demo/go/project/cmd/rocketmq", log.Default())
	if err != nil {
		log.Printf("init consumer, err=%v", err)
		return
	}

	defer func() {
		closeFn()
	}()
	err = c.Consumer()
	if err != nil {
		log.Printf("init consumer, err=%v", err)
	}
}

func processMsg(ctx context.Context, message *rocketmq.MessageView) {
	log.Printf("consume, body=%+v msg_id= %v ts=%v", string(message.GetBody()), message.GetMessageId(), time.Now().Format(time.DateTime))
}

type Consumer struct {
	simpleConsumer rocketmq.SimpleConsumer
	consumerFn     func(ctx context.Context, message *rocketmq.MessageView)
	topic          string
	maxMessageNum  int32
	logger         *log.Logger
}

func NewConsumer(endPoint []string, consumerGroup, topic string, maxMessageNum int32,
	cfn func(ctx context.Context, message *rocketmq.MessageView), logPath string, logger *log.Logger) (*Consumer, func() error, error) {

	os.Setenv("mq.consoleAppender.enabled", "true")
	os.Setenv("rocketmq.client.logLevel", "INFO")
	os.Setenv("rocketmq.client.logRoot", logPath)
	rocketmq.ResetLogger()

	c, err := rocketmq.NewSimpleConsumer(&rocketmq.Config{
		Endpoint:      strings.Join(endPoint, ";"),
		ConsumerGroup: consumerGroup,
		Credentials:   &credentials.SessionCredentials{},
	},
		rocketmq.WithAwaitDuration(common.AwaitDuration),
		rocketmq.WithSubscriptionExpressions(map[string]*rocketmq.FilterExpression{
			topic: rocketmq.SUB_ALL,
		}))

	// start
	err = c.Start()
	if err != nil {
		return nil, nil, err
	}
	// graceful stop
	fn := func() error {
		return c.GracefulStop()
	}

	return &Consumer{
		consumerFn:     cfn,
		simpleConsumer: c,
		topic:          topic,
		maxMessageNum:  maxMessageNum,
		logger:         logger,
	}, fn, err
}
func (pc *Consumer) Consumer() error {

	for {
		msgs, err := pc.simpleConsumer.Receive(context.TODO(), pc.maxMessageNum, common.InvisibleDuration)
		if err != nil {
			s, ok := rocketmq.AsErrRpcStatus(err)
			if ok {
				pc.logger.Printf("consume receive, topic=%v code=%v msg=%v err:=%v", pc.topic, s.GetCode(), s.GetMessage(), s.Error())
			} else {
				pc.logger.Printf("consume receive, topic=%v err=%v", pc.topic, err)
			}
			time.Sleep(time.Second * 3)
			continue
		}

		for _, msg := range msgs {
			pc.consumerFn(context.TODO(), msg)
			err := pc.simpleConsumer.Ack(context.TODO(), msg)
			if err != nil {
				pc.logger.Printf("consume ack, msg=%+v body=%v err=%v", msg, string(msg.GetBody()), err)
			}
		}
	}

}
