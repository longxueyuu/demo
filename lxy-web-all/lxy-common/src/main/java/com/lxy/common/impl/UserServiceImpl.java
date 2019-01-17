package com.lxy.common.impl;

import com.lxy.common.domain.User;
import com.lxy.common.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2017/4/29.
 */
@Service
public class UserServiceImpl implements UserService{
    @Override
    public List<User> getUsers()
    {
        List<User> list = new ArrayList<>(10);
        User user = new User();
        user.setUid("01");
        user.setName("lxy");
        list.add(user);

        user = new User();
        user.setUid("02");
        user.setName("lxy");
        list.add(user);

        user = new User();
        user.setUid("01");
        user.setName("lxy");
        list.add(user);

        return list;
    }

    @Override
    public List<User> getUsers(int size) {

        List<User> users = new ArrayList<>(size);
        if(size <= 0) return users;

        for(int i = 0; i < size; i++)
        {
            User user = new User();
            user.setUid(i + "");
            user.setName("lxy-" + i);
            user.setAge(i);
            users.add(user);
        }
        return users;
    }

    // lxy
    @Override
    public boolean isExist() {
        return false;
    }

}
