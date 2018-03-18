package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Coach;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/13.
 */
public interface CoachDao {

    public int queryCoachCount(Map<String, Object> map);

    public List<Coach> queryCoachList(Map<String, Object> map);

    public Coach queryCoachById(Integer coachId);

    public void deleteCoach(Integer coachId);

    public void addCoach(Coach coach);

    public void updateCoach(Coach coach);
}
