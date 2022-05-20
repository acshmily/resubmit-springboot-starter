package io.github.acshmily.resubmit.service.impl;

import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Author: Huanghz
 * description: Jedis实现
 * date:Created in 16:14 2022/5/20
 * modifyBy:
 **/
@Component
@Import(ReSubmitInterceptor.class)
public class JedisImpl implements ReSubmitService {
    @Override
    public boolean process(String key, Long tts) {
        return jedis.setnx(key, FLAG) == 1 && jedis.expire(key, tts) > 0;
    }


    public JedisImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    private final Jedis jedis;
}
