package io.github.acshmily.resubmit.config;

import io.github.acshmily.resubmit.announce.EnableRedissonReSubmit;
import io.github.acshmily.resubmit.service.ReSubmitService;
import io.github.acshmily.resubmit.service.impl.RedissonImpl;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Author: Huanghz
 * description: 配置类
 * date:Created in 15:36 2022/5/20
 * modifyBy:
 **/
@ConditionalOnBean(annotation = {EnableRedissonReSubmit.class}  )
@Configuration
public class ReSubmitRedissonConfig {
    public ReSubmitRedissonConfig( RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }
    @Bean
    public ReSubmitService reSubmitService(){
        return new RedissonImpl(redissonClient);
    }
    private final RedissonClient redissonClient;


}
