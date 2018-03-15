package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.CoachDao;
import com.urbanfit.apiserver.entity.Coach;
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
@Service("coachService")
@Transactional
public class CoachService {
    @Resource
    private CoachDao coachDao;

    public PageObject<Coach> queryCoachList(String coachName, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(coachName)){
            map.put("coachName", coachName);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil page = new PageObjectUtil<Coach>();
        return page.savePageObject(coachDao.queryCoachCount(map), coachDao.queryCoachList(map), queryInfo);
    }

    public Coach queryCoachById(Integer coachId){
        return coachDao.queryCoachById(coachId);
    }

    public String deleteCoach(Integer coachId){
        if(coachId == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        Coach coach = coachDao.queryCoachById(coachId);
        if(coach == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "数据不存在或者已经删除")) ;
        }
        coachDao.deleteCoach(coachId);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "删除教练信息成功")) ;
    }
}
