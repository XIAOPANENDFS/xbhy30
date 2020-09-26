package com.zrh.enums;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public enum MeetingStatusEnum {

    NO_START(0),
    MEETING(1),
    END(2);

    private Integer value;

    MeetingStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }}

