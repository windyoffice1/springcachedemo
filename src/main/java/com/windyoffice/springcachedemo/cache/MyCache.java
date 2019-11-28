package com.windyoffice.springcachedemo.cache;


import com.windyoffice.springcachedemo.util.JsonUtil;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

@Component
public class MyCache implements Cache {
    @Autowired
    private CacheChannel cacheChannel;

    private String name;

    private String region="text";

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public ValueWrapper get(Object o) {
        CacheObject object=cacheChannel.get(region,String.valueOf(o));

        return object==null ?null :new SimpleValueWrapper(object);
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        CacheObject object=cacheChannel.get(region,String.valueOf(o),false);
        return JsonUtil.toBean(JsonUtil.convertObjectToJSON(object.getValue()),aClass) ;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {
        cacheChannel.set(region,String.valueOf(o),String.valueOf(o1));
    }

    @Override
    public void evict(Object o) {
        cacheChannel.evict(region,String.valueOf(o));
    }

    @Override
    public void clear() {
        cacheChannel.clear(region);
    }
}
