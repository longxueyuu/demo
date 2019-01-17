package com.lxy.springboottemplate.controller;

import com.alibaba.fastjson.JSONObject;
import com.lxy.springboottemplate.dao.UserDAO;
import com.lxy.springboottemplate.domain.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Value("${range}")
    private String range;

    @Resource
    private UserDAO userDAO;

    @ApiOperation(value="用户登录", notes="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataTypeClass = String.class, paramType = "method"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "string", paramType = "method")
    })
    @ApiResponse(code=200, message = "true", response = User.class)
    @RequestMapping("login.json")
    public Object login(String userName, String password) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("msg", "OK");
        return jsonObject.toString();
    }

    @ApiOperation(value="获取所有用户信息", notes="获取所有用户详细信息列表")
    @RequestMapping("list.json")
    public Object users() {
        List<User> users = userDAO.getAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("msg", "OK");
        jsonObject.put("users", users);

        return jsonObject.toString();
    }

    @RequestMapping("add.json")
    public Object addUser(User user) {
        boolean resp = userDAO.add(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", true);
        jsonObject.put("msg", "OK");
        jsonObject.put("response", resp);

        return jsonObject.toString();
    }

}
