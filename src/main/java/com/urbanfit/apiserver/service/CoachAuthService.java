package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.CoachAuthDao;
import com.urbanfit.apiserver.entity.CoachAuth;
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
 * Created by Administrator on 2018/3/1.
 */
@Service("coachAuthService")
@Transactional
public class CoachAuthService {
    @Resource
    private CoachAuthDao coachAuthDao;

    /**
     * 添加认证
     */
    public String addCoachAuth(String coachName, String coachCardNum){
        if(StringUtils.isEmpty(coachName) || StringUtils.isEmpty(coachCardNum)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        // 查看是否认证过
        CoachAuth coachAuth = coachAuthDao.queryCoachAuthByCardNum(coachCardNum);
        if(coachAuth != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "教师证号码已认证通过")) ;
        }
        CoachAuth auth = new CoachAuth();
        auth.setCoachName(coachName);
        auth.setCoachCardNum(coachCardNum);
        coachAuthDao.addCoachAuth(auth);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加认证信息成功")) ;
    }

    /**
     * 删除认证
     */
    public String deleteCoachAuth(Integer authId){
        if(authId == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        coachAuthDao.deleteCoachAuth(authId);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "删除认证信息成功")) ;
    }

    /**
     * 修改认证信息
     */
    public String updateCoachAuth(Integer authId, String coachName, String coachCardNum){
        if(authId == null || StringUtils.isEmpty(coachName) || StringUtils.isEmpty(coachCardNum)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        CoachAuth auth = coachAuthDao.queryCoachAuthById(authId);
        if(auth == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您修改的信息不存在")) ;
        }
        // 查询认证信息是否存在
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authId", authId);
        map.put("coachCardNum", coachCardNum);
        CoachAuth coachAuth = coachAuthDao.queryCoachAuthByCardNumAndId(map);
        if(coachAuth != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "教师证号码已认证通过")) ;
        }
        auth.setCoachName(coachName);
        auth.setCoachCardNum(coachCardNum);
        coachAuthDao.updateCoachAuth(auth);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "修改认证信息成功")) ;
    }

    public CoachAuth queryCoachAuthById(Integer authId){
        return coachAuthDao.queryCoachAuthById(authId);
    }

    public PageObject<CoachAuth> queryCoachAuthList(String authInfo, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(authInfo)){
            map.put("authInfo", authInfo);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil page = new PageObjectUtil<CoachAuth>();
        return page.savePageObject(coachAuthDao.queryCoachAuthCount(map), coachAuthDao.
                queryCoachAuthList(map), queryInfo);
    }

    public String queryCoachAuth(String coachName, String coachCardNum){
        if(StringUtils.isEmpty(coachName) || StringUtils.isEmpty(coachCardNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("coachName", coachName);
        map.put("coachCardNum", coachCardNum);
        CoachAuth coachAuth = coachAuthDao.queryCoachAuth(map);
        if(coachAuth == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有查询到数据", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询认证成功", "").toString();
    }
}
