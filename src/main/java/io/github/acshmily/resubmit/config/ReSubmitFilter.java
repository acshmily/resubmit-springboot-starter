package io.github.acshmily.resubmit.config;

import io.github.acshmily.resubmit.interceptor.CustomHttpServletRequestWrapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

/**
 * Wrapper the request, fixed get input stream only once
 */
public class ReSubmitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            // check is file upload
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String type = servletRequest.getContentType();
            if(StringUtils.hasLength(type) && type.contains(MediaType.MULTIPART_FORM_DATA_VALUE)){
                servletRequest = new StandardServletMultipartResolver().resolveMultipart(servletRequest);
            }
            ServletRequest requestWrapper = new CustomHttpServletRequestWrapper(servletRequest);
            chain.doFilter(requestWrapper,response);
            return;
        }
        chain.doFilter(request,response);
    }
}
