package com.windyoffice.springcachedemo.service.impl;


import com.windyoffice.springcachedemo.common.ServerResponse;
import com.windyoffice.springcachedemo.pojo.User;
import com.windyoffice.springcachedemo.pojo.UserRequest;
import com.windyoffice.springcachedemo.service.IUserService;
import com.windyoffice.springcachedemo.util.PropertiesUtil;
import com.windyoffice.springcachedemo.util.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Value("${sign.publickey}")
    private String publicKey;

    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);


    @Cacheable(value = "myCache",key = "# username")
    @Override
    public ServerResponse<User> getUserInfo(String username) {
        logger.info("UserServiceImpl  getUserInfo");
        User user=new User();
        user.setUserName("张三");
        user.setAge(18);
        user.setAddress("测试路测试号");
        return ServerResponse.createBySuccess("获取用户信息成功",user);
    }

    @Override
    public ServerResponse<User> getUserInfoBySing(UserRequest request) {
        if(validateRequest(request)) {
            User user=new User();
            user.setUserName("张三");
            user.setAge(18);
            user.setAddress("测试路测试号");
            return ServerResponse.createBySuccess("获取用户信息成功",user);
        }else{
            return ServerResponse.createByErrorMsg("验签不通过");
        }
    }

    private Boolean  validateRequest(UserRequest request) {
        boolean flag=false;
        try {
            flag= SignUtil.checkSignWithRSA(request.getUserName(), PropertiesUtil.getProperty("sign.publickey"),request.getSignData());
        }catch (Exception e){
            flag=false;
            logger.error(e.getMessage());
        }
        return flag;
    }

}
