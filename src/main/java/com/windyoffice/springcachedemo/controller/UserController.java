package com.windyoffice.springcachedemo.controller;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.pojo.User;
import com.windyoffice.springcachedemo.pojo.UserRequest;
import com.windyoffice.springcachedemo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {

    Logger logger= LoggerFactory.getLogger(UserController.class);


    @Autowired
    private IUserService userService;

    @RequestMapping(value ="getuserinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(String userName){

        logger.info(" UserController getUserInfo");
        return userService.getUserInfo(userName);
    };


    @RequestMapping(value ="getuserinfoBySign.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfoBySign(UserRequest request){
        logger.info(" UserController getUserInfo");
        return userService.getUserInfoBySing(request);
    };
}
