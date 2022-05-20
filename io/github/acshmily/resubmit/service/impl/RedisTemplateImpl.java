package io.github.acshmily.resubmit.service.impl;

import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author: Huanghz
 * description: RedisTemplate 实现
 * date:Created in 16:04 2022/5/20
 * modifyBy:
 **/
@Component
@Import(ReSubmitInterceptor.class)
public class RedisTemplateImpl implements ReSubmitService {
    @Override
    public boolean process(String key, Long tts) {
        return Boolean.TRUE.equals(redisTemplate.boundValueOps(key).setIfAbsent(FLAG, tts, TimeUnit.SECONDS));
    }

    public RedisTemplateImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private final RedisTemplate<String,String> redisTemplate;
}
