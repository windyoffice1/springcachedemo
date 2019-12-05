package com.windyoffice.springcachedemo.service;

import com.windyoffice.springcachedemo.vo.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    User user=new User();

    @Cacheable(value = "userCache" ,key = "# id")
    public User getUserService(String id){

        user.setAge(11);
        user.setName("张三");
        return user;
    }

    @CachePut(value = "userCache" ,key = "# id")
    public User updateUserInfo(String id){
        user.setAge(12);
        user.setName("张三更新");
        return user;
    }

}
