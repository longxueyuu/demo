package logex

import (
	"errors"
	"github.com/test/project/util/fileex"
	"log"
	"os"
)

var (
	ErrEmptyPath = errors.New("empty path")
)

func GetLogger(path string, prefix string) (*log.Logger, *os.File, error) {
	if path == "" {
		return nil, nil, ErrEmptyPath
	}

	f, err := fileex.AppendFile(path)
	if err != nil {
		return nil, nil, err
	}

	l := log.New(f, prefix, log.Llongfile|log.LstdFlags)
	return l, f, nil
}
