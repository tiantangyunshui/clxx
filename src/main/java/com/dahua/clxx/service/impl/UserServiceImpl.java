package com.dahua.clxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dahua.clxx.config.TokenMap;
import com.dahua.clxx.exception.BusinessRuntimeException;
import com.dahua.clxx.mapper.UserMapper;
import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.Token;
import com.dahua.clxx.pojo.ClxxUser;
import com.dahua.clxx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Value("${dssIp}")
    private String dssIp;

    @Override
    public List<ClxxUser> queryUser(ClxxUser user) {
        return null;
    }

    @Override
    public Token login(String name, String pwd) {
        if (name == null || "".equals(name) || pwd == null || "".equals(pwd)) {
            throw new BusinessRuntimeException(500, "用户名密码不能为空");
        }
        Wrapper<ClxxUser> wrapper = Wrappers.<ClxxUser>lambdaQuery().eq(ClxxUser::getPhone, name).or().eq(ClxxUser::getUserName, name).or().eq(ClxxUser::getNo,name);
        List<ClxxUser> ll = userMapper.selectList(wrapper);
        if (ll.size() == 0) {
            throw new BusinessRuntimeException(500, "用户不存在");
        }
        if(!pwd.equals(ll.get(0).getPassword())){
            throw new BusinessRuntimeException(500, "密码不正确");
        }
        ClxxUser user = ll.get(0);
        user.setPassword(null);
        String token = UUID.randomUUID().toString().replaceAll("-","");
        Token t = new Token(token,user,System.currentTimeMillis());
        TokenMap.map.put(token,t);
        return t;
    }

    @Override
    public List<ClxxUser> getTeacher(String phone) {
        Wrapper wrapper = Wrappers.<ClxxUser>lambdaQuery().like(ClxxUser::getPhone,phone);
        List<ClxxUser> ll = userMapper.selectList(wrapper);
        for (ClxxUser user : ll) {
            user.setPassword(null);
        }
        return ll;
    }

    @Override
    public Person getStudentByToken(String token) {
        Token t = TokenMap.map.get(token);
        if(t != null){
            List<Person> list = userMapper.getStudent(t.getUser().getNo());
            if(list.size()>0){
                Person person = list.get(0);
                if(person.getFaceImg() != null){
                    person.setFaceImg(person.getFaceImg().replace("serverIp",dssIp));
                }
                return person;
            }else {
                throw new BusinessRuntimeException(500, "用户不存在");
            }
        }else {
            throw new BusinessRuntimeException(401, "token失效");
        }
    }

    @Override
    public void updUser(ClxxUser user) {
        Wrapper wrapper = Wrappers.<ClxxUser>lambdaQuery().eq(ClxxUser::getId,user.getId());
        if(user.getOldPassword()==null || "".equals(user.getOldPassword())){
            throw new BusinessRuntimeException(500, "原密码不能为空");
        }
        if(user.getPassword()==null || "".equals(user.getPassword())){
            throw new BusinessRuntimeException(500, "密码不能为空");
        }
        List<ClxxUser> ll = userMapper.selectList(wrapper);
        if(ll.size() == 0){
            throw new BusinessRuntimeException(500, "用户不存在");
        }
        if(!user.getOldPassword().equals(ll.get(0).getPassword())){
            throw new BusinessRuntimeException(500, "原密码输入错误");
        }
        int index = userMapper.updateById(user);
        if(index == 0){
            throw new BusinessRuntimeException(500, "更新失败");
        }
    }
}
