package com.zrh.test;

import java.text.SimpleDateFormat;
import java.util.Timer;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Timer timer = new Timer();
        MyTask task = new MyTask();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        timer.schedule(task, sdf.parse("2020-09-25 11:22:50"), 1000);
    }
}

