package com.zrh.entity;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class Dept extends BaseEntity {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}