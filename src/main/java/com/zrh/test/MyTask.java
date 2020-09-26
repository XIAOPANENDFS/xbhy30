package com.zrh.test;

import com.zrh.service.MeetingService;

import java.util.TimerTask;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MyTask extends TimerTask {

    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        meetingService.updateStatusTimer();

    }
}
