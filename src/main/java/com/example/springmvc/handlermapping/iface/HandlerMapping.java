package com.example.springmvc.handlermapping.iface;

import javax.servlet.http.HttpServletRequest;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 17:10
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public interface HandlerMapping {

    Object getHandler(HttpServletRequest request);
}
