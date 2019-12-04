package com.windyoffice.springcachedemo.config;

import com.windyoffice.springcachedemo.cache.MyCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CacheConfig {

    @Autowired
    private MyCache myCache;

    @Bean
    @Primary
    public SimpleCacheManager myCacheManager(){
        SimpleCacheManager simpleCacheManager=new SimpleCacheManager();
        List<Cache> cacheList=new ArrayList<Cache>();
        myCache.setName("userCache");
        myCache.setRegion("user");
        cacheList.add(myCache);
        simpleCacheManager.setCaches(cacheList);
        return simpleCacheManager;

    };

}
