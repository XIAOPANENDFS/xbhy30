package com.zrh.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrh.entity.Menu;
import com.zrh.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebServlet("/menu/*")
public class MenuServlet extends BaseServlet {

    private MenuService menuService = new MenuService();

    protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Menu> list = menuService.listAll();

        List<Menu> parentList = new ArrayList<>();
        List<Menu> sonList = new ArrayList<>();

        parentList = list.stream().filter(n -> {
            return n.getType().equals("1");
        }).collect(Collectors.toList());

        sonList = list.stream().filter(n -> {
            return n.getType().equals("2");
        }).collect(Collectors.toList());

        Map<String, List<Menu>> map = new HashMap<>();
        map.put("parent", parentList);
        map.put("son", sonList);
        response.getWriter().write(JSONObject.toJSONString(map));
    }

}
