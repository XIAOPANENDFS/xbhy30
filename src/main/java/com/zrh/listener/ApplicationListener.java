package com.zrh.listener;

import com.zrh.test.MyTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        MyTask myTask = new MyTask();
        //一分钟 1000*60
        timer.schedule(myTask, new Date(), 1000*10);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
