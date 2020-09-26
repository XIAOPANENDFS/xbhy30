package com.zrh.dao;

import com.zrh.entity.Dept;
import com.zrh.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class UserDao extends BaseDao {

    public User checkLogin(String username, String password) {
        String sql = "select * from user where username=? and password = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> listPage() {
        String sql = "select * from user ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List<Dept> listDept() {
        String sql = "select * from dept ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dept.class));
    }

    public User getUserById(Integer id) {
        String sql = "select * from user where id=? ";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updatePic(Integer id, String pic) {
        String sql = "update user set pic = ? where id = ? ";
        jdbcTemplate.update(sql, pic, id);
    }

    public List<User> getUserByDeptId(Integer deptId) {
        String sql = "select * from user where dept_id = ?  ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), deptId);
    }

}
