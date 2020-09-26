package com.zrh.service;

import com.zrh.dao.MeetingDao;
import com.zrh.entity.Meeting;
import com.zrh.enums.MeetingStatusEnum;
import com.zrh.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MeetingService {
    private MeetingDao meetingDao = new MeetingDao();

    public List<Meeting> listPage() {
        return meetingDao.listPage();
    }

    public void addMeeting(Meeting meeting) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        meeting.setPublishDate(sdf.format(new Date()));

        //默认状态时是未开始
        meeting.setStatus(MeetingStatusEnum.NO_START.getValue());

        // [1,2,3]
        meeting.setMakeUser(Arrays.toString(meeting.getMakeUsers()));

        meetingDao.addMeeting(meeting);
    }

    /***
     * @decription 定时修改状态
     * @author admin
     * @date 2020/9/25 11:31
     * @params []
     * @return void
     */
    public void updateStatusTimer() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //第一步：查询所有数据（状态是0的不需要）
        List<Meeting> list = meetingDao.listPage();
        list.stream().forEach(n -> {

            Long startDate = DateUtil.getTimeByStr(n.getStartTime());
            Long endDate = DateUtil.getTimeByStr(n.getEndTime());
            Long nowDate = new Date().getTime();

            Meeting meeting = new Meeting();
            meeting.setId(n.getId());
            if (startDate > nowDate) {
                //未开始
            } else if (startDate <= nowDate && endDate > nowDate) {
                //进行中
                meeting.setStatus(MeetingStatusEnum.MEETING.getValue());
                System.out.println("定时任务-------会议id：" + meeting.getId() + "，修改了状态");
                meetingDao.updateStatusById(meeting);
            } else {
                //已结束
                meeting.setStatus(MeetingStatusEnum.END.getValue());
                System.out.println("定时任务-------会议id：" + meeting.getId() + "，修改了状态");
                meetingDao.updateStatusById(meeting);
            }

        });
    }

    public Meeting getMeetingById(Integer id) {
        return meetingDao.getMeetingById(id);
    }

    public List<Integer> listUserId(Integer meetingId) {
        return meetingDao.listUserId(meetingId);
    }

    public void joinMeeting(Integer meetingId, Integer userId) {
        meetingDao.joinMeeting(meetingId, userId);
    }

}
