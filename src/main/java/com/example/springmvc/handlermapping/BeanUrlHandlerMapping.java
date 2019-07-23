package com.example.springmvc.handlermapping;

import com.example.springmvc.handler.UserAddHandler;
import com.example.springmvc.handler.UserDeleteHandler;
import com.example.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 17:11
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class BeanUrlHandlerMapping implements HandlerMapping {

    Map<String, Object> urlHandlerMapping = new HashMap<>();

    public BeanUrlHandlerMapping() {
        // 获取Spring容器
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = urlHandlerMapping.get(requestURI);
        return handler;
    }
}
