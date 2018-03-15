package com.urbanfit.apiserver.service;


import com.urbanfit.apiserver.dao.ActivityMessageDao;
import com.urbanfit.apiserver.entity.ActivityMessage;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyubo on 2018/3/15.
 */
@Service("activityMessageService")
@Transactional
public class ActivityMessageService {

    @Resource
    private ActivityMessageDao activityMessageDao;

    public PageObject<ActivityMessage> queryActivityMessageList(String title, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(title)){
            map.put("title", title);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil page = new PageObjectUtil<ActivityMessage>();
        return page.savePageObject(activityMessageDao.queryActivityMessageCount(map), activityMessageDao.
                queryActivityMessageList(map), queryInfo);
    }

    public ActivityMessage queryActivityMessageById(Integer messageId){
        return activityMessageDao.queryActivityMessageById(messageId);
    }

    public String deleteActivityMessage(Integer messageId){
        if(messageId == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        ActivityMessage activityMessage = activityMessageDao.queryActivityMessageById(messageId);
        if (activityMessage == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "数据不存在或者已经删除")) ;
        }
        activityMessageDao.deleteActivityMessage(messageId);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "删除活动资讯信息成功")) ;
    }
}
