package com.example.springmvc.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Despriction: 模仿Servlet类的写法
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 16:10
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public class UserAddHandler {

    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF8");
        response.getWriter().write("添加成功！");
    }
}
