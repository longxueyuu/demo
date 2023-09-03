package http

import (
	"net/http/httputil"
	"testing"
)

func TestHttputil(t *testing.T) {
	_ = httputil.ReverseProxy{}
}
