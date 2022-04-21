package view

import (
	"encoding/json"
	"net/http"
)

type RespData struct {
	ErrorCode int         `json:"error_code"`
	ErrorMsg  string      `json:"error_msg"`
	Data      interface{} `json:"data"`
}

func setHeader(w *http.ResponseWriter) {
	(*w).Header().Set("Content-Type", "application/json")
}

func JsonSucceed(w http.ResponseWriter, r *http.Request, data interface{}) error {
	setHeader(&w)
	err := json.NewEncoder(w).Encode(RespData{ErrorCode: 0, Data: data})
	return err
}

func JsonFail(w http.ResponseWriter, r *http.Request, code int, msg string) error {
	setHeader(&w)
	resp := RespData{ErrorCode: code, ErrorMsg: msg, Data: map[string]string{}}
	err := json.NewEncoder(w).Encode(resp)
	return err
}

type AuthCode struct {
	Code string `json:"code"`
	UID  string `json:"uid"`
}

func GetCode(w http.ResponseWriter, r *http.Request, pathParams map[string]string) {
	req := struct {
		UID string `json:"uid"`
	}{}

	err := json.NewDecoder(r.Body).Decode(&req)
	if err != nil {
		_ = JsonFail(w, r, http.StatusInternalServerError, "json decode")
		return
	}

	data := &AuthCode{
		Code: "123456",
		UID:  req.UID,
	}

	_ = JsonSucceed(w, r, data)
}
