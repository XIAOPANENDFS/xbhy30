package com.zrh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrh.constants.Constant;
import com.zrh.entity.User;
import com.zrh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private UserService userService = new UserService();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 验证账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        String code = request.getParameter("code");

        User loginUser = userService.checkLogin(username, password);

        HttpSession session = request.getSession();

        if (loginUser != null) {

            session.setAttribute(Constant.SESSION_LOGIN, loginUser);
            session.setMaxInactiveInterval(30 * 60);

            //判断是否勾选7天免登录,如果勾选则把登录信息放到cookie中
            if ("1".equals(remember)) {

                String jsonStr = JSONObject.toJSONString(loginUser);
                //先编码，在解码，解决cookie不能存中文
                Cookie cookieLoginUser = new Cookie(Constant.COOKIE_LOGIN, URLEncoder.encode(jsonStr, "utf-8"));
                cookieLoginUser.setMaxAge(7 * 24 * 60 * 60);
                cookieLoginUser.setPath("/");
                response.addCookie(cookieLoginUser);
            }

            request.setAttribute("loginUser", (User) session.getAttribute(Constant.SESSION_LOGIN));
            request.getRequestDispatcher("/html/common/home.jsp").forward(request, response);
        } else {

            response.sendRedirect("/index.jsp");
        }

    }

}
