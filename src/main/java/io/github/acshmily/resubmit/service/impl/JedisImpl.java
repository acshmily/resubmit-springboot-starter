package io.github.acshmily.resubmit.service.impl;

import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

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
        try (Jedis jedis = jedisPool.getResource()) {
            return "OK".equals(jedis.set(FLAG + key, "1", new SetParams().nx().ex(tts)));
        } catch (Exception e) {
            //
        }
        return true;
    }


    public JedisImpl(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private final JedisPool jedisPool;
}
