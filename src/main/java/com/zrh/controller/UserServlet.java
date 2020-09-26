package com.zrh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrh.entity.Dept;
import com.zrh.entity.User;
import com.zrh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.listPage();
        request.setAttribute("list", users);
        request.getRequestDispatcher("/html/user/list.jsp").forward(request, response);
    }

    protected void listDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Dept> list = userService.listDept();
        response.getWriter().write(JSONObject.toJSONString(list));
    }

    protected void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));

        List<Dept> list = userService.listDept();

        request.setAttribute("user", user);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/html/user/update.jsp").forward(request, response);

    }

    protected void getUserByDeptId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptId = request.getParameter("deptId");
        List<User> list = userService.getUserByDeptId(Integer.valueOf(deptId));
        response.getWriter().write(JSONObject.toJSONString(list));
    }
}

