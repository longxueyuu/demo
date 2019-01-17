package com.lxy.common.service;

import com.lxy.common.domain.User;

import java.util.List;

/**
 * Created by lxy on 2017/4/29.
 */
public interface UserService {
    List<User> getUsers();

    List<User> getUsers(int size);

    boolean isExist();
}
