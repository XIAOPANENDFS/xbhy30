package com.zrh.dao;

import com.zrh.utils.DbUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class BaseDao {

    public static JdbcTemplate jdbcTemplate = new JdbcTemplate(DbUtil.getDataSource());

}
