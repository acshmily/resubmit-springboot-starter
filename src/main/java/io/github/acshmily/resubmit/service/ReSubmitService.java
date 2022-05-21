package io.github.acshmily.resubmit.service;

/**
 * Author: Huanghz
 * description: 提交接口定义
 * date:Created in 15:50 2022/5/20
 * modifyBy:
 **/
public interface ReSubmitService {
    /**
     * @param key
     * @param tts
     * @return true 通过、 false 不通过
     */
    boolean process(String key,Long tts);
    String FLAG = "ReSubmit_Starter_";
}
