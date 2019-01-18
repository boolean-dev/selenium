package com.onegene.server.selenium.config;

import com.onegene.server.selenium.filter.BaseUrlInterceptor;
import com.onegene.server.selenium.interceptor.ExecuteTimeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName InterceptorConfig
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/17 23:32
 **/
@Configuration
public class InterceptorConfig  extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseUrlInterceptor()).addPathPatterns("/**").addPathPatterns("/**");
        registry.addInterceptor(new ExecuteTimeInterceptor()).addPathPatterns("/wechat/**");
    }

}
