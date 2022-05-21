package io.github.acshmily.resubmit.config;

import io.acshmily.cachetemplate.client.service.CacheTemplate;
import io.github.acshmily.resubmit.announce.EnableCacheTemplateReSubmit;
import io.github.acshmily.resubmit.service.ReSubmitService;
import io.github.acshmily.resubmit.service.impl.CacheTemplateImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Author: Huanghz
 * description: 配置类
 * date:Created in 15:36 2022/5/20
 * modifyBy:
 **/
@ConditionalOnBean(annotation = {EnableCacheTemplateReSubmit.class}  )
@Configuration
public class ReSubmitCacheTemplateConfig {
    public ReSubmitCacheTemplateConfig(CacheTemplate cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
    }
    @Bean
    public ReSubmitService reSubmitService(){
        return new CacheTemplateImpl(cacheTemplate);
    }
    private final CacheTemplate cacheTemplate;


}
