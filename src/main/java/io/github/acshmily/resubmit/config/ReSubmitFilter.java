package io.github.acshmily.resubmit.config;

import io.github.acshmily.resubmit.interceptor.CustomHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Wrapper the request, fixed get input stream only once
 */
public class ReSubmitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest){
            ServletRequest requestWrapper = new CustomHttpServletRequestWrapper((HttpServletRequest) request);
            chain.doFilter(requestWrapper,response);
            return;
        }
        chain.doFilter(request,response);
    }
}
