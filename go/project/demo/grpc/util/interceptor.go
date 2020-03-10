package util

import (
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
	"log"
	"os"
	"runtime"
)

var PanicLogger = log.New(os.Stderr, "GRPC PANIC:", log.LstdFlags|log.Lshortfile)

func CustomRecoveryHandlerFunc(p interface{}) (err error) {
	err = status.Errorf(codes.Internal, "panic: %v", p)
	var buf [1 << 10]byte
	runtime.Stack(buf[:], true)
	PanicLogger.Printf("panic, err=%v\n%s", err, buf)
	return err
}
