package io.github.acshmily.resubmit.utils;


import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;

/**
 * key处理工具
 */
public class KeyUtils {
    /**
     * 根据object获得key
     * @param string
     * @return
     */
    public static String getKey(@NotNull final String string){
        Assert.notNull(string,"getKey method parameter can not null");
        return DigestUtils.md5DigestAsHex(string.getBytes(StandardCharsets.UTF_8));
    }

}
