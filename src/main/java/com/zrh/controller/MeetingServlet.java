package com.zrh.controller;

import com.zrh.entity.Meeting;
import com.zrh.enums.MeetingStatusEnum;
import com.zrh.service.MeetingService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {

    private MeetingService meetingService = new MeetingService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数据
        List<Meeting> list = meetingService.listPage();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/html/meeting/list.jsp").forward(request, response);
    }

    public void addMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Meeting meeting = new Meeting();
        try {
            BeanUtils.populate(meeting, map);
            meetingService.addMeeting(meeting);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/meeting/list").forward(request, response);
    }

    public void getMeetingById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> map = new HashMap<>();

        Integer loginUserId = loginUser.getId();

        String id = request.getParameter("id");
        Meeting meeting = meetingService.getMeetingById(Integer.valueOf(id));

        //所有已经参加会议的人的id
        List<Integer> userIdList = meetingService.listUserId(Integer.valueOf(id));

        //应到人数  [1,2,3]
        String makeUser = meeting.getMakeUser();
        Integer should = makeUser.split(",").length;
        map.put("should", should.toString());

        //实到人数
        if (userIdList == null) {
            map.put("realCount", "0");
        } else {
            map.put("realCount", String.valueOf(userIdList.size()));
        }

        boolean b = makeUser.contains(loginUserId.toString());
        if (b) {

            if (userIdList.contains(loginUserId)) {
                //说明已经参加
                map.put("flag", "2");
            } else {
                //未参加
                map.put("flag", "3");
            }
        } else {
            //不需要
            map.put("flag", "1");
        }

        request.setAttribute("meeting", meeting);
        request.setAttribute("map", map);
        request.getRequestDispatcher("/html/meeting/detail.jsp").forward(request, response);
    }

    public void joinMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        //验证是否能参加会议（会议必须是未开始的会议才能参加）
        Meeting meeting = meetingService.getMeetingById(Integer.valueOf(id));
        if (meeting.getStatus().equals(MeetingStatusEnum.NO_START.getValue())) {
            //可以参加
            meetingService.joinMeeting(Integer.valueOf(id), loginUser.getId());
        } else {

        }

        request.setAttribute("id", id);
        request.getRequestDispatcher("/meeting/getMeetingById").forward(request, response);

    }
}
