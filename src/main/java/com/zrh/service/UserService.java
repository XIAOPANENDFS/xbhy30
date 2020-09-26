package com.zrh.service;

import com.zrh.dao.UserDao;
import com.zrh.entity.Dept;
import com.zrh.entity.User;

import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class UserService {

    private UserDao userDao = new UserDao();

    public User checkLogin(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    public List<User> listPage() {
        return userDao.listPage();
    }

    public List<Dept> listDept() {
        return userDao.listDept();
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id, pic);
    }

    public List<User> getUserByDeptId(Integer deptId) {
        return userDao.getUserByDeptId(deptId);
    }

}
