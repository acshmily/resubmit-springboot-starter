package io.github.acshmily.resubmit.config;


import io.github.acshmily.resubmit.interceptor.ReSubmitInterceptor;
import io.github.acshmily.resubmit.service.ReSubmitService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.FilterRegistration;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(reSubmitInterceptor());
    }

    @Bean
    public FilterRegistrationBean reSubmitFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ReSubmitFilter());
        registrationBean.setName("reSubmitFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;


    }

    @Bean
    public ReSubmitInterceptor reSubmitInterceptor(){
        return new ReSubmitInterceptor(reSubmitService);
    }

    @Resource
    private ReSubmitService reSubmitService;

}
