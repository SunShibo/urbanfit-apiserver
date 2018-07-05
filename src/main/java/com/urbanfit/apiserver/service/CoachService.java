package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.CoachDao;
import com.urbanfit.apiserver.entity.Coach;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.text.MessageFormat;
import java.util.Date;
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

    public String uploadHeadPortrait(MultipartFile file){
        if(file == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "paramError")) ;
        }
        if( file.getSize() > 2 * 1024 * 1024){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "big")) ;
        }
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType = file.getContentType();
        String random = RandomUtil.generateString(4);
        //获得文件后缀名称
        String imageType = contentType.substring(contentType.indexOf("/") + 1);
        String yyyyMMdd = DateUtils.formatDate(DateUtils.DATE_PATTERN_PLAIN, new Date());
        String yyyyMMddHHmmss = DateUtils.formatDate(DateUtils.LONG_DATE_PATTERN_PLAIN, new Date());
        String fileName = yyyyMMddHHmmss + random + "." + imageType;
        String urlMsg = SystemConfig.getString("coach_head_portrait_url");
        urlMsg = MessageFormat.format(urlMsg, new Object[]{yyyyMMdd, fileName});
        String headPortrait = urlMsg.replace("/attached", SystemConfig.getString("img_file_root"));
        String msgUrl = SystemConfig.getString("client_upload_base");
        String tmpFileUrl = msgUrl + urlMsg;
        File ff = new File(tmpFileUrl.substring(0, tmpFileUrl.lastIndexOf('/')));
        if (!ff.exists()) {
            ff.mkdirs();
        }
        byte[] tmp = null;
        try {
            tmp = file.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtils.getFileFromBytes(tmp, tmpFileUrl);

        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("headPortrait", headPortrait);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    public String addCoach(Coach coach){
        if(coach == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        if(StringUtils.isEmpty(coach.getCoachName()) || StringUtils.isEmpty(coach.getCoachTitle())
                || StringUtils.isEmpty(coach.getHeadPortrait())|| StringUtils.isEmpty(coach.getIntroduce())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        coachDao.addCoach(coach);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加教练信息成功")) ;
    }

    public String updateCoach(Coach coach){
        if(coach == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        if(coach.getCoachId() == null || StringUtils.isEmpty(coach.getCoachName()) || StringUtils.isEmpty(
                coach.getCoachTitle()) || StringUtils.isEmpty(coach.getHeadPortrait())|| StringUtils.isEmpty(
                coach.getIntroduce())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        Coach coachDetail = coachDao.queryCoachById(coach.getCoachId());
        if(coachDetail == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "数据不存在")) ;
        }
        coachDao.updateCoach(coach);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "修改教练信息成功")) ;
    }

    public String queryCoachList(QueryInfo queryInfo){
        if(queryInfo == null) {
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());

        PageObjectUtil page = new PageObjectUtil<Coach>();
        PageObject<Coach> pager = page.savePageObject(coachDao.queryCoachCount(map), coachDao.
                queryCoachList(map), queryInfo);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstCoach", JsonUtils.getJsonString4JavaListDate(pager.getDatas(), DateUtils.LONG_DATE_PATTERN));
        jo.put("totalRecord", pager.getTotalRecord());
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
