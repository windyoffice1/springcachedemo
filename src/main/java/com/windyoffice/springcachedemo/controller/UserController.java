package com.windyoffice.springcachedemo.controller;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.entity.User;
import com.windyoffice.springcachedemo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    @RequestMapping(value ="adduser",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> addUser(@RequestBody User user){
       return userService.addUserInfo(user);
    }

    @RequestMapping(value = "finduser",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> findUserById( @RequestParam("id") String id){
        return userService.findUserById(id);
    }



}
