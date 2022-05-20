package io.github.acshmily.resubmit.config;

import io.acshmily.cachetemplate.client.service.CacheTemplate;
import io.github.acshmily.resubmit.announce.EnableCacheTemplateReSubmit;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;


/**
 * Author: Huanghz
 * description: 配置类
 * date:Created in 15:36 2022/5/20
 * modifyBy:
 **/
@ConditionalOnBean(annotation = {EnableCacheTemplateReSubmit.class}  )
public class ReSubmitCacheTemplateConfig {



    public ReSubmitCacheTemplateConfig(CacheTemplate cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
    }
    private final CacheTemplate cacheTemplate;


}
