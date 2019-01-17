package com.lxy.controller;

import com.lxy.common.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lxy on 15/01/2018.
 */
@RestController
@RequestMapping("json")
public class JsonController {

    @RequestMapping("request")
    public User getUsers(@RequestBody User user) {
        return user;
    }
}
