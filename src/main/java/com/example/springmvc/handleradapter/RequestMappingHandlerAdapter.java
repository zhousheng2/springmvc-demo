package com.example.springmvc.handleradapter;

import com.example.springmvc.annotation.ResponseBody;
import com.example.springmvc.handleradapter.iface.HandlerAdapter;
import com.example.springmvc.handlermapping.HandlerMethod;
import com.example.springmvc.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-25 16:06
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof HandlerMethod);
    }

    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Object object = handlerMethod.getHandler();

        // 获取方法参数（参数绑定）
        Object[] args = getParameters(request, method);

        // 执行处理器方法
        Object returnValue = method.invoke(object, args);
        
        // 处理返回结果
        handleReturnValue(returnValue, response, method);

    }

    private void handleReturnValue(Object returnValue, HttpServletResponse response, Method method) {
        try {
            if (method.isAnnotationPresent(ResponseBody.class)) {
                // 响应数据（前后端分离）
                if(returnValue instanceof String) {
                    response.setContentType("text/plain;charset=UTF8");
                    response.getWriter().write(returnValue.toString());
                } else if (returnValue instanceof Map) {
                    response.setContentType("application/json;charset=UTF8");
                    response.getWriter().write(JsonUtils.object2Json(returnValue));
                }
            } else {
                // 展示视图
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private Object[] getParameters(HttpServletRequest request, Method method) {
        // key=value&key1=value1...
        // 获取请求url中的参数信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 获取handler中的参数信息
        Parameter[] parameters = method.getParameters();

        List<Object> args = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 获取参数名称(如果不做特殊处理，name值是arg0，arg1)
            String name = parameter.getName();
            Class<?> type = parameter.getType();

            String[] value = parameterMap.get(name);
            if (type == List.class) {
                args.add(value);
            } else if (type == Integer.class) {
                args.add(Integer.parseInt(value[0]));
            } else if (type == String.class) {
                args.add(value[0]);
            }
        }
        return args.toArray();
    }
}
