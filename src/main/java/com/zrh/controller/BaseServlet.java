package com.zrh.controller;

import com.zrh.constants.Constant;
import com.zrh.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class BaseServlet extends HttpServlet {

    public User loginUser = new User();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loginUser = (User) session.getAttribute(Constant.SESSION_LOGIN);

        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        //截取到请求路径的最后一个字符串 (login2 list)
        String method = split[split.length - 1];

        Class c = this.getClass();
        try {
            Method declaredMethod = c.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
