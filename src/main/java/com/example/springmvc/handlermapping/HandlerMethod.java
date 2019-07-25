package com.example.springmvc.handlermapping;

import java.lang.reflect.Method;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-25 15:36
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class HandlerMethod {

    private Method method;

    private Object handler;

    public HandlerMethod() {
    }

    public HandlerMethod(Method method, Object handler) {
        this.method = method;
        this.handler = handler;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }
}
