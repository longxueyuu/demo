package com.lxy.springboottemplate.dao;

import com.lxy.springboottemplate.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDAO {

    @Select("select * from user")
    List<User> getAll();

    @Insert({"insert into user (name, age) values (#{user.name}, #{user.age})"})
    boolean add(@Param("user") User user);
}
