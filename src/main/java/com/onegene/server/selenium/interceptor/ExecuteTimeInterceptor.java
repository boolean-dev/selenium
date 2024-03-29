package com.onegene.server.selenium.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ExecuteTimeInterceptor
 * @Descriiption TODO
 * @Author yanjiantao
 * @Date 2019/1/17 22:35
 **/
public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

    /** logger */
    protected Logger logger = LogManager.getLogger(ExecuteTimeInterceptor.class);

    /** "开始时间"参数名称 */
    private static final String START_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".START_TIME";
    /** "执行时间"参数名称 */
    public static final String EXECUTE_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".EXECUTE_TIME";
    /** 重定向视图名称前缀 */
    private static final String REDIRECT_VIEW_NAME_PREFIX = "redirect:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE_NAME);
        if (startTime == null) {
            startTime = System.currentTimeMillis();
            request.setAttribute(START_TIME_ATTRIBUTE_NAME, startTime);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Long executeTime = (Long) request.getAttribute(EXECUTE_TIME_ATTRIBUTE_NAME);
        if (executeTime == null) {
            Long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE_NAME);
            Long endTime = System.currentTimeMillis();
            executeTime = endTime - startTime;
            request.setAttribute(START_TIME_ATTRIBUTE_NAME, startTime);
        }

        if (modelAndView != null) {
            String viewName = modelAndView.getViewName();
            if (!StringUtils.startsWith(viewName, REDIRECT_VIEW_NAME_PREFIX)) {
                modelAndView.addObject(EXECUTE_TIME_ATTRIBUTE_NAME, executeTime);
            }
        }
        logger.warn("[" + handler + "] executeTime: " + executeTime + "ms");
    }

}
