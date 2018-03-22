package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.ActivityMessageDao;
import com.urbanfit.apiserver.entity.ActivityMessage;
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

    public String addActivityMessage(ActivityMessage activityMessage){
        if(activityMessage == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        if(StringUtils.isEmpty(activityMessage.getTitle()) || StringUtils.isEmpty(activityMessage.getContent())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        // 添加数据
        activityMessageDao.addActivityMessage(activityMessage);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加活动资讯信息成功")) ;
    }

    public String updateActivityMessage(ActivityMessage activityMessage){
        if(activityMessage == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        if(activityMessage.getMessageId() == null || StringUtils.isEmpty(activityMessage.getTitle())
                || StringUtils.isEmpty(activityMessage.getContent())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        ActivityMessage message = activityMessageDao.queryActivityMessageById(activityMessage.getMessageId());
        if(message == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "数据不存在")) ;
        }
        activityMessageDao.updateActivityMessage(activityMessage);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "修改活动资讯信息成功")) ;
    }

    public String uploadThumbnails(MultipartFile file){
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
        String urlMsg = SystemConfig.getString("activity_message_image_url");
        urlMsg = MessageFormat.format(urlMsg, new Object[]{yyyyMMdd, fileName});
        String thumbnailsUrl = urlMsg.replace("/attached", SystemConfig.getString("img_file_root"));
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
        jo.put("thumbnailsUrl", thumbnailsUrl);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    public String queryActivityMessageList(QueryInfo queryInfo){
        if(queryInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());

        PageObjectUtil page = new PageObjectUtil<ActivityMessage>();
        PageObject<ActivityMessage> pager = page.savePageObject(activityMessageDao.queryActivityMessageCount(map),
                activityMessageDao.queryActivityMessageList(map), queryInfo);
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstMessage", JsonUtils.getJsonString4JavaListDate(pager.getDatas(), DateUtils.LONG_DATE_PATTERN));
        jo.put("totalRecord", pager.getTotalRecord());
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryActivityMessageDetail(Integer messageId){
        if(messageId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ActivityMessage activityMessage = activityMessageDao.queryActivityMessageById(messageId);
        if(activityMessage == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "查询不到数据", "").toString();
        }

        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("activityMessage", JsonUtils.getJsonObject4JavaPOJO(activityMessage, DateUtils.LONG_DATE_PATTERN));

        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }
}
