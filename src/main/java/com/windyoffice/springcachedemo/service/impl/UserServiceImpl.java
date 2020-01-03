package com.windyoffice.springcachedemo.service.impl;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.dao.UserMapper;
import com.windyoffice.springcachedemo.entity.User;
import com.windyoffice.springcachedemo.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("iUserService")
@Transactional
public class UserServiceImpl implements IUserService {

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

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

    @Cacheable(cacheNames = "userCache" ,key = "# id")
    @Override
    public ServerResponse<User> findUserById(String id) {
        logger.info("==============finduserbyId from db==============");
        User user=userMapper.selectByPrimaryKey(Integer.valueOf(id));
        if(user!=null){
            return ServerResponse.createBySuccess(user);
        }else{
            return ServerResponse.createByErrorMsg("查完此人");
        }
    }
}
