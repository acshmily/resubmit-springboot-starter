package io.github.acshmily.resubmit.config;


import io.github.acshmily.resubmit.announce.EnableJedisReSubmit;
import io.github.acshmily.resubmit.service.ReSubmitService;
import io.github.acshmily.resubmit.service.impl.JedisImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * Author: Huanghz
 * description: 配置类
 * date:Created in 15:36 2022/5/20
 * modifyBy:
 **/
@ConditionalOnBean(annotation = {EnableJedisReSubmit.class}  )
@Configuration
public class ReSubmitJedisonfig {
    public ReSubmitJedisonfig(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
    @Bean
    public ReSubmitService reSubmitService(){
        return new JedisImpl(jedisPool);
    }
    private final JedisPool jedisPool;


}
