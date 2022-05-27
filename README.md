#  resubmit-springboot-starter

![Version](https://img.shields.io/badge/release-v1.1-brightgreen?style=flat-square)
[![Reference](https://img.shields.io/badge/Redis-Reference-blue.svg?style=flat-square)](https://hub.docker.com/_/redis)
[![Reference](https://img.shields.io/badge/SpringBoot-Reference-blue.svg?style=flat-square)](https://github.com/acshmily/resubmit-springboot-starter)
![License](https://img.shields.io/:License-Apache2.0-green.svg?style=flat-square)

A stater of SpringBoot Start,Resolve **POST**,**PUT** method resubmit,you can set ttl time and choose need to announce controller method.

the function need redis cache the request features,support RedisTemplate/Jedis/Redisson/CacheTemplate.
## Maven Example

```xml
<dependency>
    <groupId>com.github.acshmily</groupId>
    <artifactId>resubmit-spring-boot-starter</artifactId>
    <version>1.1</version>
</dependency>
```
## Gradle Example

```Gradle
implementation 'com.github.acshmily:resubmit-spring-boot-starter:1.0'
```

## How to use?
### step1 
import resubmit-spring-boot-starter to your project

### step2 
choose your project redis client match announce,and add your springboot application main class.

| RedisClientType | EnableAnnounce               |
|-----------------|------------------------------|
| RedisTemplate   | @EnableRedisTemplateReSubmit |
| Jedis           | @EnableJedisReSubmit         |
 | Redisson        | @EnableRedissonResubmit      |
| CacheTemplate   | @EnableCacheTemplateResubmit |

Example project you can visit https://github.com/acshmily/resubmit-springboot-tester .

### step3
announce your controller
```java
@RequestMapping("")
@RestController
public class IndexController {
    @RequestMapping("")
    @ReSubmit(tts = 60L)
    public String hello(@RequestBody String s) {

        return s;
    }
}
```

