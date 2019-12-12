package com.windyoffice.springcachedemo.service.impl;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.dao.UserMapper;
import com.windyoffice.springcachedemo.entity.User;
import com.windyoffice.springcachedemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("iUserService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ServerResponse<String> addUserInfo(User user) {
       int count= userMapper.insert(user);
       if(count>0){
           return ServerResponse.createBySuccessMsg("新增用户成功");
       }else{
           return ServerResponse.createByErrorMsg("新增用户失败");
       }
    }
}
