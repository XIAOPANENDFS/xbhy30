package com.zrh.shedule;

import com.zrh.service.MeetingService;

import java.util.TimerTask;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MeetingTask extends TimerTask {

    private boolean isRunning = false;

    //定时任务执行体
    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            meetingService.updateStatusTimer();
            isRunning = false;
        }
    }
}