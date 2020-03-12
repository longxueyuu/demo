package httpex

import (
	"encoding/json"
	"net/http"
)

type P map[string]interface{}

type RespBody struct {
	ErrCode int         `json:"err_code"`
	ErrMsg  string      `json:"err_msg"`
	Data    interface{} `json:"data,omitempty"`
}

func setCommonRespHeader(w *http.ResponseWriter) {
	(*w).Header().Set("Content-Type", "application/json")
}

func JSONOK(w http.ResponseWriter, data interface{}) error {
	w.WriteHeader(http.StatusOK)
	setCommonRespHeader(&w)

	return json.NewEncoder(w).Encode(RespBody{
		Data: data,
	})
}

func JSONXErr(w http.ResponseWriter, code int, msg string, data interface{}) error {
	return JSONErr(w, http.StatusOK, code, msg, data)
}

func JSONErr(w http.ResponseWriter, httpStatus, code int, msg string, data interface{}) error {
	w.WriteHeader(httpStatus)
	setCommonRespHeader(&w)

	return json.NewEncoder(w).Encode(RespBody{
		ErrCode: code,
		ErrMsg:  msg,
		Data:    data,
	})
}
