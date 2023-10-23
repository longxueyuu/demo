package com.example.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
class Resp {
    private int code;
    private String message;
}

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = { BizException.class })
    public Resp handleParameterVerificationException(@NonNull Exception e) {

        log.error("Exception: {}", e.getMessage());
        var resp = new Resp();
        resp.setCode(HttpStatus.BAD_REQUEST.value());
        resp.setMessage(e.getMessage()
        );
        return resp;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Resp handleException(@NonNull Exception e) {

        log.error("Exception: {}", e.getMessage());
        var resp = new Resp();
        resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        resp.setMessage(e.getMessage()
        );
        return resp;
    }
}
