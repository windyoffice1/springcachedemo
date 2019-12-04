package com.windyoffice.springcachedemo.service;

import com.windyoffice.springcachedemo.vo.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Cacheable(value = "userCache" ,key = "# id")
    public User getUserService(String id){
        User user=new User();
        user.setAge(11);
        user.setName("张三");
        return user;
    }
}
