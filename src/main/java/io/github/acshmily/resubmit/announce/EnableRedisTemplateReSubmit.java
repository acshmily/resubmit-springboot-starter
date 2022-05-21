package io.github.acshmily.resubmit.announce;

import io.github.acshmily.resubmit.config.ReSubmitRedisTemplateConfig;
import io.github.acshmily.resubmit.config.WebMvcConfig;
import io.github.acshmily.resubmit.service.impl.RedisTemplateImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author: Huanghz
 * description: 使用RedisTemplate开启重复提交拦截
 * date:Created in 15:16 2022/5/20
 * modifyBy:
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ReSubmitRedisTemplateConfig.class, WebMvcConfig.class})
public @interface EnableRedisTemplateReSubmit {

}
