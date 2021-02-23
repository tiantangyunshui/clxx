package com.dahua.clxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dahua.clxx.config.TokenMap;
import com.dahua.clxx.exception.BusinessRuntimeException;
import com.dahua.clxx.mapper.UserMapper;
import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.Token;
import com.dahua.clxx.pojo.User;
import com.dahua.clxx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryUser(User user) {
        return null;
    }

    @Override
    public Token login(String name, String pwd) {
        if (name == null || "".equals(name) || pwd == null || "".equals(pwd)) {
            throw new BusinessRuntimeException("500", "用户名密码不能为空");
        }
        Wrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getNo, name).or().eq(User::getUserName, name);
        List<User> ll = userMapper.selectList(wrapper);
        if (ll.size() == 0) {
            throw new BusinessRuntimeException("500", "用户不存在");
        }
        if(!pwd.equals(ll.get(0).getPassword())){
            throw new BusinessRuntimeException("500", "密码不正确");
        }
        User user = ll.get(0);
        user.setPassword(null);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        Token t = new Token(token,user,System.currentTimeMillis());
        TokenMap.map.put(token,t);
        return t;
    }

    @Override
    public List<User> getTeacher(String phone) {
        Wrapper wrapper = Wrappers.<User>lambdaQuery().like(User::getPhone,phone);
        List<User> ll = userMapper.selectList(wrapper);
        for (User user : ll) {
            user.setPassword(null);
        }
        return ll;
    }

    @Override
    public List<Person> getStudent(String no) {
        return userMapper.getStudent(no);
    }
}
