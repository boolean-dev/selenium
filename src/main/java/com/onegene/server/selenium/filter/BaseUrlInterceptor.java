package com.onegene.server.selenium.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName BaseUrlInterceptor
 * @Descriiption BaseUrlInterceptor
 * @Author yanjiantao
 * @Date 2019/1/17 21:29
 **/
@Configuration
public class BaseUrlInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        String path = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        if (port == 80) {
            String basePath = scheme + "://" + serverName + path;
            request.setAttribute("base", basePath);
        } else {
            String basePath = scheme + "://" + serverName + ":" + port + path;
            request.setAttribute("base", basePath);
        }
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        if (port == 80) {
            String basePath = scheme + "://" + serverName + path;
            request.setAttribute("base", basePath);
        } else {
            String basePath = scheme + "://" + serverName + ":" + port + path;
            request.setAttribute("base", basePath);
        }
    }
}
