package com.zrh.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zrh.constants.Constant;
import com.zrh.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebFilter("/*")
public class MyFilter  extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        // 规定： 登录地址只能是http://localhost:8080/index.jsp
        if (requestURI.endsWith("/") || requestURI.endsWith("index.jsp")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    String key = c.getName();
                    if (Constant.COOKIE_LOGIN.equals(key)) {
                        //说明cookie中有的登录信息

                        //第一步：把cookie中的登录信息取出来，然后放到session中
                        String value = c.getValue();
                        //解码后是json字符串类型
                        value = URLDecoder.decode(value, "utf-8");
                        //把json字符串转换成User对象
                        User loginUser = JSONObject.parseObject(value, new TypeReference<User>() {
                        });
                        session.setAttribute(Constant.SESSION_LOGIN, loginUser);
                        session.setMaxInactiveInterval(30 * 60);

                        request.setAttribute("loginUser", loginUser);
                        //第二步：跳转到首页
                        request.getRequestDispatcher("/html/common/home.jsp").forward(request, response);
                        chain.doFilter(request, response);
                        return;
                    }
                }
            }
        } else if (requestURI.endsWith("login") ||
                requestURI.endsWith("/menu/listAll") ||
                requestURI.endsWith("/img/getPicCode")

        ) {

        } else {
            //判断session中是否有登录信息
            Object obj = session.getAttribute(Constant.SESSION_LOGIN);
            if (obj == null) {
                //非法登录
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
        }

        request.setAttribute("loginUser", (User) session.getAttribute(Constant.SESSION_LOGIN));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

