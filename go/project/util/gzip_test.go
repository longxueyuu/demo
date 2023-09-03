package util

import (
	"bytes"
	"compress/gzip"
	"io/ioutil"
	"log"
	"testing"
)

func TestGzip(t *testing.T) {
	es := []byte("hello world, test gzipdadfadafd")
	var b bytes.Buffer
	w := gzip.NewWriter(&b)

	_, _ = w.Write(es)
	_ = w.Flush()
	w.Close()

	r, _ := gzip.NewReader(&b)
	defer r.Close()
	data, err := ioutil.ReadAll(r)
	log.Printf("ungzip, v=%v err=%v", string(data), err)

	var sourceData, unzipData []byte
	var errX error
	d := ioutil.NopCloser(bytes.NewReader(data))
	r, err = gzip.NewReader(d)
	if err != nil {
		sourceData, errX = ioutil.ReadAll(d)
	} else {
		unzipData, _ = ioutil.ReadAll(r)
	}
	log.Printf("ungzip again, sourceData=%v unzipData=%v err=%v errX=%v", string(sourceData), string(unzipData), err, errX)
}
