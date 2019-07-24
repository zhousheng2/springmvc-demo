package com.example.springmvc.handlermapping;

import com.example.springframework.beans.aware.BeanFactoryAware;
import com.example.springframework.beans.config.BeanDefinition;
import com.example.springframework.beans.factory.BeanFactory;
import com.example.springmvc.handlermapping.iface.HandlerMapping;
import org.apache.commons.lang3.StringUtils;

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
public class BeanNameHandlerMapping implements HandlerMapping, BeanFactoryAware {

    // 通过Aware接口，注入beanFactory实例
    private BeanFactory beanFactory;

    Map<String, Object> urlHandlerMap = new HashMap<>();

    public void initMethod() {
        // 读取Spring容器，获取bean信息，该信息的name即为url
        // 通过bean工厂，获取所有bean对象（beanDefiition对象）
        Map<String, BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions.values()) {
            String beanName = beanDefinition.getBeanName();
            // 通过beanDefiition对象的name属性，去检查url中是否以“/”开头
            if (!StringUtils.isEmpty(beanName) && beanName.startsWith("/")) {
                // 如果是就像该name与url建立映射关系
                urlHandlerMap.put(beanName, beanFactory.getBean(beanName));
            }
        }

    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = urlHandlerMap.get(requestURI);
        return handler;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
