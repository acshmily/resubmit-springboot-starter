package io.github.acshmily.resubmit.announce;

import io.github.acshmily.resubmit.config.WebMvcConfig;
import io.github.acshmily.resubmit.service.impl.JedisImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Author: Huanghz
 * description: 使用JedisImpl开启重复提交拦截
 * date:Created in 15:16 2022/5/20
 * modifyBy:
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JedisImpl.class, WebMvcConfig.class})
public @interface EnableJedisReSubmit {

}
