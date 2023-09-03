package common

import (
	"github.com/google/uuid"
	"time"
)

const (
	TopicDemo = "normal"

	ConsumeGroupDemo = "CID_NORMAL"
)

const (
	AwaitDuration           = time.Second * 5
	MaxMessageNum     int32 = 4
	InvisibleDuration       = time.Second * 20
)

var (
	// rocketmq server endpoints
	EndPoints = []string{"localhost:8081"}
)

func UUID() string {
	u, _ := uuid.NewUUID()
	return u.String()
}
