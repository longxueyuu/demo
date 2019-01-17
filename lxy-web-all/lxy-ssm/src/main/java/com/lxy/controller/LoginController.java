package com.lxy.controller;


import com.alibaba.fastjson.JSONObject;
import com.lxy.common.domain.User;
import com.lxy.common.impl.UserServiceImpl;
import com.lxy.common.service.UserService;
import com.lxy.domain.GeneralRequestParam;
import com.lxy.kotlin.domain.Video;
import com.lxy.kotlin.impl.VideoServiceImpl;
import com.lxy.kotlin.service.VideoService;
import com.lxy.service.LoginService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/4/29.
 */


@Controller
@RequestMapping("home")
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    private VideoService videoService = new VideoServiceImpl();

    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    @ResponseBody
    public String login(){
        JSONObject json = new JSONObject();
        List<String> data = new ArrayList<>();
        data.add("记忆大师");
        data.add("速度与激情8");
        data.add("银河护卫队2");
        json.put("code", 0);
        json.put("desc", "lxy-ssm idea maven project");
        json.put("data", data);

        List<User> users = userService.getUsers();
        json.put("users", users);
        int i = 0;
        if (i == 1) {
            throw new RuntimeException("login Runtime Exception");
        }

        return json.toString();
    }

    @RequestMapping("users")
    @ResponseBody
    public Object getUsers(int size) throws Exception {
        LOG.info("getUsers size=" + size);
        JSONObject json = new JSONObject();
        List<User> users = userService.getUsers(size);
        json.put("code", 0);
        json.put("total", users.size());
        json.put("users", users);
        int i = 0;
        if (i == 1) {
            throw new Exception("users Exception");
        }

        return json;
    }

    @RequestMapping("kotlin/users")
    @ResponseBody
    public String getUsersByKotlin(int size)
    {
        JSONObject json = new JSONObject();
        List<Video> videos = videoService.getVideos(size);
        json.put("code", 0);
        json.put("total", videos.size());
        json.put("videos", videos);
        return json.toString();
    }

    @RequestMapping("check/user")
    @ResponseBody
    public String checkuser(GeneralRequestParam generalRequestParam) {
        JSONObject json = new JSONObject();
        String addResp = loginService.add(generalRequestParam);
        String existResp = loginService.exist(generalRequestParam);

        json.put("addResp: ", addResp);
        json.put("existResp: ", existResp);

        return json.toJSONString();
    }



}
