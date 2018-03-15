package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.ActivityMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by wangyubo on 2018/3/15.
 */
public interface ActivityMessageDao {

    public int queryActivityMessageCount(Map<String, Object> map);

    public List<ActivityMessage> queryActivityMessageList(Map<String, Object> map);

    public ActivityMessage queryActivityMessageById(Integer messageId);

    public void deleteActivityMessage(Integer messageId);
}
