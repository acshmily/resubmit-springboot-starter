package io.github.acshmily.resubmit.announce;

import java.lang.annotation.*;

/**
 * Author: Huanghz
 * description: 声明注解
 * date:Created in 14:52 2022/5/20
 * modifyBy:
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReSubmit {
    String value() default "";
    // 过期时间，对于单体不适用
    long tts() default 1;

    String msg() default "重复提交";
}
