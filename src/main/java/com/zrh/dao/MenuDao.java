package com.zrh.dao;

import com.zrh.entity.Menu;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MenuDao extends BaseDao {

    public List<Menu> listAll() {
        String sql = "select * from meun";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Menu.class));
    }
}