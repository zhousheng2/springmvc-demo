package com.example.springmvc.handleradapter;

import com.example.springmvc.handler.iface.HttpRequestHandler;
import com.example.springmvc.handleradapter.iface.HandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhousheng
 * @date 2019-07-23 21:47
 */
public class HttpRequestHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        // 适配
        return (handler instanceof HttpRequestHandler);
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 执行处理器（适配模式[适配器、适配者、目标类]中的适配者类）
        ((HttpRequestHandler) handler).handleRequest(request, response);
    }
}
