package com.lxy.springboottemplate.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("redis")
public class RedisController {

    private static final Logger LOG = LoggerFactory.getLogger(RedisController.class);

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("ping.json")
    public Object ping() {
        JSONObject jsonObject = new JSONObject();

        String pong = redisTemplate.execute((RedisCallback<String>) connection -> connection.ping());

        jsonObject.put("success", pong);
        return jsonObject;
    }

    @RequestMapping("query.json")
    public Object query(String keyType, String key, String fields) {
        JSONObject jsonObject = new JSONObject();

        String value = redisTemplate.opsForValue().get(key);
        jsonObject.put("success", true);
        jsonObject.put(key, JSONObject.parse(value));
        return jsonObject;
    }
}
