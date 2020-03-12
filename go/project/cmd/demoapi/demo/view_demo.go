package demo

import (
	"github.com/test/project/env"
	"github.com/test/project/util/httpex"
	"net/http"
)

func HandleDemo(w http.ResponseWriter, r *http.Request) {
	vs := r.URL.Query()
	name := vs.Get("name")
	env.ApiLog.Printf("HandleDemo: info, name=%v", name)
	if name == "" {
		env.ErrLog.Printf("HandleDemo: bad request")
		_ = httpex.JSONErr(w, http.StatusBadRequest, http.StatusBadRequest, "bad request", httpex.P{})
		return
	}

	_ = httpex.JSONOK(w, httpex.P{"name": name})
}
