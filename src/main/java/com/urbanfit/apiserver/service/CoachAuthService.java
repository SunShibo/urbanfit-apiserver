package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.CoachAuthDao;
import com.urbanfit.apiserver.entity.CoachAuth;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
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
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查看是否认证过
        CoachAuth coachAuth = coachAuthDao.queryCoachAuthByCardNum(coachCardNum);
        if(coachAuth != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "教师证号码已认证通过")) ;
        }
        CoachAuth auth = new CoachAuth();
        auth.setCoachName(coachName);
        auth.setCoachCardNum(coachCardNum);
        coachAuthDao.addCoachAuth(coachAuth);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000", "添加认证信息成功")) ;
    }

    /**
     * 删除认证
     */
    public String deleteCoachAuth(Integer authId){
        if(authId == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        coachAuthDao.deleteCoachAuth(authId);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000", "删除认证信息成功")) ;
    }

    /**
     * 修改认证信息
     */
    public String updateCoachAuth(Integer authId, String coachName, String coachCardNum){
        if(authId == null || StringUtils.isEmpty(coachName) || StringUtils.isEmpty(coachCardNum)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查询认证信息是否存在
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("authId", authId);
        map.put("coachCardNum", coachCardNum);
        CoachAuth coachAuth = coachAuthDao.queryCoachAuthByCardNumAndId(map);
        if(coachAuth != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "教师证号码已认证通过")) ;
        }
        coachAuth.setCoachName(coachName);
        coachAuth.setCoachCardNum(coachCardNum);
        coachAuthDao.updateCoachAuth(coachAuth);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000", "修改认证信息成功")) ;
    }
}
