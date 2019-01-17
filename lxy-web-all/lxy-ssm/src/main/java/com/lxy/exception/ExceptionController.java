package com.lxy.exception;

import com.alibaba.fastjson.JSONObject;
import com.lxy.common.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lxy on 08/01/2018.
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {
    @RequestMapping("test")
    public String test(HttpServletRequest request) {

        User user = (User)request.getAttribute("user");
        Exception ex = (Exception) request.getAttribute("exception");
        JSONObject json = new JSONObject();

        json.put("code", 0);
        json.put("user", user);
        json.put("exception", ex);
        json.put("desc", "lxy-ssm idea maven project");
        json.put("data", "exception test");

        return json.toString();
    }
}
