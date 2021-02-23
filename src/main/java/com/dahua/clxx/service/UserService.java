package com.dahua.clxx.service;

import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.Token;
import com.dahua.clxx.pojo.User;
import java.util.List;

public interface UserService {

    /**
     * 查询用户列表
     */
    List<User> queryUser(User user);

    /**
     * 根据用户名密码获取用户
     */
    Token login(String name, String pwd);

    /**
     * 获取审批教师信息
     */
    List<User> getTeacher(String phone);

    /**
     * 获取学生信息
     */
    List<Person> getStudent(String no);
}
