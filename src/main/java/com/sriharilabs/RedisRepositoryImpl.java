package com.sriharilabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.concurrent.TimeUnit;

@Repository
@Slf4j
public class RedisRepositoryImpl {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisCacheManager RedisCacheManager;

    public void add(final String orderNumber) {
        redisTemplate.opsForValue().set(orderNumber, null,24, TimeUnit.HOURS);
    }
    public Boolean isOrderExists(final String orderNumber){
    	
        return redisTemplate.hasKey(orderNumber);
    }
    
    public void get() {
        log.info("tis ...."+redisTemplate.keys("*"));
    }
}
