package com.example.springmvc.handleradapter.iface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhousheng
 * @date 2019-07-23 21:44
 */
public interface HandlerAdapter {

    /**
     * 该适配器是否和该处理器适配
     *
     * @param handler
     * @return
     */
    boolean supports(Object handler);


    /**
     * 根据查找到的适配器去执行处理器
     *
     * @param handler
     * @param request
     * @param response
     * @throws Exception
     */
    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
