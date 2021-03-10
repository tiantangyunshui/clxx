package com.dahua.clxx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dahua.clxx.config.Result;
import com.dahua.clxx.pojo.Person;
import com.dahua.clxx.pojo.ClxxUser;
import com.dahua.clxx.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    private Result<List<ClxxUser>> getTeacher(@ApiParam(value = "手机号" , required=true )@RequestParam("phone") String phone){
        return new Result<>(userService.getTeacher(phone));
    }
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    private Result<JSONObject> getStudent(HttpServletRequest request){
        JSONObject obj = new JSONObject();
        obj.put("roles",new String[]{"admin"});
        obj.put("user",userService.getStudentByToken(request.getHeader("token")));
        return new Result<>(obj);
    }

}
