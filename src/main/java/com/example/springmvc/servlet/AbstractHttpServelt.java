package com.example.springmvc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Despriction: 抽象模板方法设计模式
 * @Author: zhousheng
 * @CreatedTime: 2019-07-23 15:53
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
public abstract class AbstractHttpServelt extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDispatch(request, response);
    }

    public abstract void doDispatch(HttpServletRequest request, HttpServletResponse response);
}
