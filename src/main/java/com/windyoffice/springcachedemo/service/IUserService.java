package com.windyoffice.springcachedemo.service;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.entity.User;

public interface IUserService {

    ServerResponse<String> addUserInfo(User user);

}
