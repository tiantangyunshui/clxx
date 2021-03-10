package com.dahua.clxx.controller;

import com.dahua.clxx.config.Result;
import com.dahua.clxx.pojo.Token;
import com.dahua.clxx.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result<Token> login(@RequestParam("name") String name, @RequestParam("pwd") String pwd){
        return new Result<>(userService.login(name, pwd));
    }

}
