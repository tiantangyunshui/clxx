package com.dahua.clxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<Person> getStudent(String no);
}
