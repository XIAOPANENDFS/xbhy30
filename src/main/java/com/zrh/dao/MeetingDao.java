package com.zrh.dao;

import com.zrh.entity.Meeting;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @auth ZRH
 * @date 2020/9/22
 * @Description
 */
public class MeetingDao extends BaseDao {

    public List<Meeting> listPage() {
        String sql = "SELECT " +
                "m.*, " +
                "d.NAME deptName  " +
                "FROM " +
                "meeting m " +
                "LEFT JOIN dept d ON d.id = m.dept_id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Meeting.class));
    }

    public void addMeeting(Meeting meeting) {
        String sql = "insert into meeting(id, dept_id,title,content, publish_date,start_time,end_time,status,make_user) values (null,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                meeting.getDeptId(),
                meeting.getTitle(),
                meeting.getContent(),
                meeting.getPublishDate(),
                meeting.getStartTime(),
                meeting.getEndTime(),
                meeting.getStatus(),
                meeting.getMakeUser());
    }

    public void updateStatusById(Meeting meeting) {
        String sql = "update meeting set status = ? where id = ? ";
        jdbcTemplate.update(sql,
                meeting.getStatus(),
                meeting.getId());
    }

    public Meeting getMeetingById(Integer id) {
        String sql = "select * from meeting where id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Meeting.class), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public List<Integer> listUserId(Integer meetingId) {
        String sql = "SELECT user_id from meeting_join where meeting_id = ? ";
        return jdbcTemplate.queryForList(sql, Integer.class, meetingId);
    }

    public void joinMeeting(Integer meetingId, Integer userId) {
        String sql = "insert into meeting_join(meeting_id,user_id) values(?,?)";
        jdbcTemplate.update(sql, meetingId, userId);
    }
}