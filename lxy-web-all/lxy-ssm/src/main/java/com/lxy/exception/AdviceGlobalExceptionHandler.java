package com.lxy.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxy on 08/01/2018.
 */
@ControllerAdvice
public class AdviceGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handlerException(Exception exception, HttpServletRequest request) {
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("exception", exception);
        json.put("data", "advice exception test");
        return json;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Object handlerException(RuntimeException exception, HttpServletRequest request) {
        JSONObject json = new JSONObject();

        json.put("code", 1);
        json.put("exception", exception);
        json.put("data", "advice runtime exception test");
        return json;
    }
}
