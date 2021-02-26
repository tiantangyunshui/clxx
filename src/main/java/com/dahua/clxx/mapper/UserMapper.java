package com.dahua.clxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.PersonCard;
import com.dahua.clxx.pojo.ClxxUser;

import java.util.List;

public interface UserMapper extends BaseMapper<ClxxUser> {

    List<Person> getStudent(String no);

    List<PersonCard> getCard(List<Long> personIds);
}
