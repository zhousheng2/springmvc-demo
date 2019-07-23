package com.example.springmvc.handler.iface;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Despriction: TODO
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 16:32
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public interface HttpRequestHandler {

    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
