package io.github.acshmily.resubmit.service.impl;

import io.acshmily.cachetemplate.client.service.CacheTemplate;
import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Author: Huanghz
 * description: cacheTemplate 实现类
 * date:Created in 15:53 2022/5/20
 * modifyBy:
 **/
@Component
@Import(ReSubmitInterceptor.class)
public class CacheTemplateImpl implements ReSubmitService {
    @Override
    public boolean process(String key, Long tts) {
        return cacheTemplate.stringSetIfAbsent(FLAG + key,"1", tts,TimeUnit.SECONDS);
    }
    public CacheTemplateImpl(CacheTemplate cacheTemplate) {
        this.cacheTemplate = cacheTemplate;
    }
    private final CacheTemplate cacheTemplate;
}
