package util

import (
	"bytes"
	"compress/gzip"
	"io/ioutil"
	"log"
	"testing"
)

func TestGzip(t *testing.T) {
	es := []byte("hello")
	var b bytes.Buffer
	w := gzip.NewWriter(&b)

	_, _ = w.Write(es)
	_ = w.Flush()
	w.Close()

	r, _ := gzip.NewReader(&b)
	defer r.Close()
	data, err := ioutil.ReadAll(r)
	log.Printf("ungzip, v=%v err=%v", string(data), err)
}
