package com.zrh.enums;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public enum SessionEnum {

    SESSION_PIC_CODE("session_pic_code"),
    SESSION_LOGIN("session_login"),
    ;

    private String value;

    SessionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}
