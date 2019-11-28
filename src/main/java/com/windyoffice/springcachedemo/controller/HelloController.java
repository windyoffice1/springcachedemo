package com.windyoffice.springcachedemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/gethello")
    @ResponseBody
    public String getHello(){
        return "hello";
    }
}
