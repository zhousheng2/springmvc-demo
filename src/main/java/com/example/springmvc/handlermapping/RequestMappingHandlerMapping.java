package com.example.springmvc.handlermapping;

import com.example.springframework.beans.aware.BeanFactoryAware;
import com.example.springframework.beans.config.BeanDefinition;
import com.example.springframework.beans.factory.BeanFactory;
import com.example.springmvc.annotation.Controller;
import com.example.springmvc.annotation.RequestMapping;
import com.example.springmvc.handlermapping.iface.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-25 15:31
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class RequestMappingHandlerMapping implements HandlerMapping, BeanFactoryAware {

    private BeanFactory beanFactory;
    // 建立URL和谁的映射关系？是Controller类吗
    // 使用注解方式开发的处理器里面，其实真正的handler对象是Controller类中的Method
    // 也就是说一个url对应一个method对象，但是要执行method对象，需要Controller的对象
    // 所以我们使用一个组合类去封装Method和Controller对象或者类
    // 这个类HandlerMethod对象（这也是注解方式下的真正的handler对象）
    Map<String, HandlerMethod> urlHandlerMethodMap = new HashMap<>();

    // 初始化
    public void initMethod() {
        try {
            // 获取容器中的所有bean
            List<String> beanNames = beanFactory.getBeanNamesByType(Object.class);
            for (String beanName : beanNames) {
                Map<String, BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
                BeanDefinition beanDefinition = beanDefinitions.get(beanName);
                String className = beanDefinition.getBeanClassName();
                Class<?> clazz = Class.forName(className);
                // 如果bean是带有@Controller注解的，再进行下一步处理
                if (isHandler(clazz)) {
                    // 再去判断带有@Controller注解的类中的方法上面是否带有@RequestMapping注解
                    Method[] declaredMethods = clazz.getDeclaredMethods();
                    for (Method method : declaredMethods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            // 获取requestMapping注解中的url
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String url = requestMapping.value();
                            // 获取该方法对象Method，封装到HandlerMethod对象中
                            HandlerMethod handlerMethod = new HandlerMethod(method, beanFactory.getBean(beanName));
                            // 建立映射关系
                            urlHandlerMethodMap.put(url, handlerMethod);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isHandler(Class<?> aClass) {
        return (aClass.isAnnotationPresent(Controller.class) || aClass.isAnnotationPresent(RequestMapping.class));
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return urlHandlerMethodMap.get(requestURI);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
