package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.CoachAuth;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/1.
 */
public interface CoachAuthDao{

    public void addCoachAuth(CoachAuth coachAuth);

    public CoachAuth queryCoachAuthByCardNum(String coachCardNum);

    public CoachAuth queryCoachAuthByCardNumAndId(Map<String, Object> map);

    public void updateCoachAuth(CoachAuth coachAuth);

    public void deleteCoachAuth(Integer authId);

    public CoachAuth queryCoachAuthById(Integer authId);

    public int queryCoachAuthCount(Map<String, Object> map);

    public List<CoachAuth> queryCoachAuthList(Map<String, Object> map);

    public CoachAuth queryCoachAuth(Map<String, Object> map);
}
