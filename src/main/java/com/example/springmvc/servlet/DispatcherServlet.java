package com.example.springmvc.servlet;

import com.example.springmvc.handleradapter.HttpRequestHandlerAdapter;
import com.example.springmvc.handleradapter.iface.HandlerAdapter;
import com.example.springmvc.handlermapping.BeanNameHandlerMapping;
import com.example.springmvc.handlermapping.SimpleHandlerMapping;
import com.example.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Despriction: 请求分发（策略模式、适配器模式）
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 17:11
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class DispatcherServlet extends AbstractHttpServelt {

    private static final long serialVersionUID = -5582760765376805456L;

    List<HandlerMapping> handlerMappings = new ArrayList<>();
    List<HandlerAdapter> handlerAdapters = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        handlerMappings.add(new SimpleHandlerMapping());
        handlerMappings.add(new BeanNameHandlerMapping());

        handlerAdapters.add(new HttpRequestHandlerAdapter());
    }

    /**
     * 请求分发给处理器类
     *
     * @param request
     * @param response
     */
    @Override
    public void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1、获取请求:URi:/userAdd
            // 2、根据请求处理对应的处理器类（策略模式）
            Object handler = getHandler(request);
            // 3、处理器类处理请求（策略模式，适配器模式）
            // 找到合适的适配器，适配器也是有很多策略的
            HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
            // 根据查找到的适配器去执行处理器
            if (handlerAdapter == null) {
                return;
            }
            handlerAdapter.handleRequest(handler, request, response);



          /*  if (handler instanceof UserAddHandler) {
                ((UserAddHandler) handler).handleRequest(request, response);
            } else if (handler instanceof UserDeleteHandler) {
                ((UserDeleteHandler) handler).handleRequest(request, response);
            } else if (handler instanceof HttpRequestHandler) {
                ((HttpRequestHandler) handler).handleRequest(request, response);
            }*/
            //  4、响应请求
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据处理器查找适配器
     *
     * @param handler
     * @return
     */
    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        return null;
    }

    /**
     * 根据请求获取处理类，委托给另一个类查找HandleMapping
     *
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        // 根据请求获取处理类：普通查找方法、Map方式、XML方式
        for (HandlerMapping handlerMapping : handlerMappings) {
            Object handler = handlerMapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }
}
