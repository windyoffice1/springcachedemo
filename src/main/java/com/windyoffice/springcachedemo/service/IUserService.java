package com.windyoffice.springcachedemo.service;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.pojo.User;
import com.windyoffice.springcachedemo.pojo.UserRequest;

public interface IUserService {


    ServerResponse<User> getUserInfo(String username);

    ServerResponse<User> getUserInfoBySing(UserRequest request);


}
