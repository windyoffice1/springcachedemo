package com.windyoffice.springcachedemo.config;

import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.cache.support.J2CacheCacheManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CacheConfig {


    @Autowired
    private CacheChannel cacheChannel;

    @Bean
    @Primary
    public J2CacheCacheManger myCacheManager(){
        J2CacheCacheManger cacheCacheManger=new J2CacheCacheManger(cacheChannel);
        List<String> cacheNames=new ArrayList<>();
        cacheNames.add("userCache");
        cacheCacheManger.setCacheNames(cacheNames);
        return  cacheCacheManger;
    };

}
