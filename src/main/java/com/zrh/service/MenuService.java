package com.zrh.service;

import com.zrh.dao.MenuDao;
import com.zrh.entity.Menu;

import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MenuService {

    private MenuDao menuDao = new MenuDao();

    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
