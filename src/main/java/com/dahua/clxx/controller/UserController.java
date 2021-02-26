package com.dahua.clxx.controller;

import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.ClxxUser;
import com.dahua.clxx.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    private List<ClxxUser> getTeacher(@ApiParam(value = "手机号" , required=true )@RequestParam("phone") String phone){
        return userService.getTeacher(phone);
    }
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    private List<Person> getStudent(@ApiParam(value = "学号" , required=true )@RequestParam("no") String no){
        return userService.getStudent(no);
    }

}
