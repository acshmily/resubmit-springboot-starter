package io.github.acshmily.resubmit.announce;

import io.acshmily.cachetemplate.client.service.impl.CacheTemplateImpl;
import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author: Huanghz
 * description: 使用CacheTemplate开启重复提交拦截
 * date:Created in 15:16 2022/5/20
 * modifyBy:
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CacheTemplateImpl.class})
public @interface EnableCacheTemplateReSubmit {

}
