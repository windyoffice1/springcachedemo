package com.windyoffice.springcachedemo.controller;

import com.windyoffice.springcachedemo.service.UserService;
import com.windyoffice.springcachedemo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UserService userService;

    @RequestMapping("/gethello")
    @ResponseBody
    public String getHello(){
        return "hello";
    }

    @RequestMapping(value = "/getuserinfo",method = {RequestMethod.POST})
    @ResponseBody
    public User getUserInfo(){
        return userService.getUserService("123");
    }
}
