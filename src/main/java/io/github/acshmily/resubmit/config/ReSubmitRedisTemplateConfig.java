package io.github.acshmily.resubmit.config;

import io.github.acshmily.resubmit.announce.EnableRedisTemplateReSubmit;
import io.github.acshmily.resubmit.service.ReSubmitService;
import io.github.acshmily.resubmit.service.impl.RedisTemplateImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * Author: Huanghz
 * description: 配置类
 * date:Created in 15:36 2022/5/20
 * modifyBy:
 **/
@ConditionalOnBean(annotation = {EnableRedisTemplateReSubmit.class}  )
@Configuration
public class ReSubmitRedisTemplateConfig {
    public ReSubmitRedisTemplateConfig(RedisTemplate<String,String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    @Bean
    public ReSubmitService reSubmitService(){
        return new RedisTemplateImpl(redisTemplate);
    }
    private final RedisTemplate<String,String> redisTemplate;


}
