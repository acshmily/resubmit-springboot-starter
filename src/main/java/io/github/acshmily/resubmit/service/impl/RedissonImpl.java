package io.github.acshmily.resubmit.service.impl;

import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author: Huanghz
 * description: Redisson实现
 * date:Created in 16:46 2022/5/20
 * modifyBy:
 **/
@Component
@Import(ReSubmitInterceptor.class)
public class RedissonImpl implements ReSubmitService {
    @Override
    public boolean process(String key, Long tts) {
        RLock rLock = redissonClient.getLock(FLAG + key);
        if(rLock.isLocked()){
            return false;
        }else{
            rLock.lock(tts,TimeUnit.SECONDS);
            return true;
        }


    }

    public RedissonImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private final RedissonClient redissonClient;
}
