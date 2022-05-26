package io.github.acshmily.resubmit.interceptor;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.acshmily.resubmit.announce.ReSubmit;
import io.github.acshmily.resubmit.service.ReSubmitService;
import io.github.acshmily.resubmit.utils.KeyUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Author: Huanghz
 * description: 重复提交拦截控制器
 * date:Created in 15:25 2022/5/20
 * modifyBy:
 **/
@Component
public class ReSubmitInterceptor implements HandlerInterceptor {

    public ReSubmitInterceptor(ReSubmitService reSubmitService) {
        this.reSubmitService = reSubmitService;
    }

    /**
     * check should need to check ReSubmit function
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) handler;
        } catch (ClassCastException e) {
            return true;
        }
        Method method = handlerMethod.getMethod();
        // to find ReSubmit annotation
        ReSubmit reSubmit = AnnotationUtils.findAnnotation(method,ReSubmit.class);
        boolean isReSubmitAnnotation = reSubmit != null;
        if(isReSubmitAnnotation){
            CustomHttpServletRequestWrapper customHttpServletRequestWrapper = new CustomHttpServletRequestWrapper(request);
            if(!customHttpServletRequestWrapper.getBody().isEmpty() && !reSubmitService.process( KeyUtils.getKey(customHttpServletRequestWrapper.getBody()),reSubmit.tts())){
                errorResponse(response,reSubmit);
                return false;
            }
        }

        return true;

    }
    /**
     * 返回校验失败
     *
     * @param res
     * @throws IOException
     */
    private void errorResponse(HttpServletResponse res,ReSubmit reSubmit) {
        try(OutputStreamWriter osw = new OutputStreamWriter(res.getOutputStream(), StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(osw, true) )
        {
            res.setContentType("application/json;charset=utf-8");
            res.setStatus(HttpServletResponse.SC_ACCEPTED);
            HashMap<String,String> resultVO = new HashMap<>(3);
            resultVO.put("code",String.valueOf(HttpServletResponse.SC_ACCEPTED));
            resultVO.put("msg",reSubmit.msg());
            resultVO.put("data","");
            String jsonStr = OBJECT_MAPPER.writeValueAsString(resultVO);
            writer.write(jsonStr);
            writer.flush();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private final ReSubmitService reSubmitService;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
    public static final String[] NEED_TO_CHECK_METHOD = {"POST","PUT"};

}
