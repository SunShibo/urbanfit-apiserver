package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.CourseDao;
import com.urbanfit.apiserver.dao.CourseSizeDao;
import com.urbanfit.apiserver.entity.Course;
import com.urbanfit.apiserver.entity.CourseSize;
import com.urbanfit.apiserver.entity.bo.CourseSizeBo;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.util.*;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Administrator on 2018/3/2.
 */
@Service("courseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseSizeDao courseSizeDao;

    /**
     * 添加课程数据
     */
    public String addCourse(String courseName, String storeIds, String courseSizeInfo, String sizePriceInfo,
                            String introduce, Integer courseType){
        if(StringUtils.isEmpty(courseName) || StringUtils.isEmpty(storeIds) || StringUtils.isEmpty(courseSizeInfo)
                || StringUtils.isEmpty(sizePriceInfo) || StringUtils.isEmpty(introduce) || courseType == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseType(courseType);
        course.setIntroduce(introduce);
        course.setStoreId("," + storeIds + ",");
        course.setCreateTime(new Date());
        // 添加课程信息
        courseDao.addCourse(course);
        // 添加课程规格
        List<CourseSizeBo> lstSize = JsonUtils.getList4Json(courseSizeInfo, CourseSizeBo.class);
        if(CollectionUtils.isEmpty(lstSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        for (CourseSizeBo size : lstSize){
            CourseSize courseSize = new CourseSize(size.getSizeTypeName(), null, course.getCourseId());
            courseSizeDao.addCourseSize(courseSize);
            // 添加数据
            List<CourseSizeBo> lstSizeName = JsonUtils.getList4JsonArray(size.getSizeNameInfo(), CourseSizeBo.class);
            for (CourseSizeBo sizeName : lstSizeName){
                CourseSize courseSizeName = new CourseSize(sizeName.getSizeName(), courseSize.getSizeId(),
                        course.getCourseId());
                courseSizeDao.addCourseSize(courseSizeName);        // 添加数据
            }
        }
        // 添加课程规格价格
        /**
         * [{"sizeTypeId":1,"sizeTypeName":"颜色","sizeNameInfo":[{"sizeNameId":1,"sizeName":"黑色"},{"sizeNameId":2,"sizeName":"白色"}]}]
         */
        /**
         * [{"courseSize":[{"sizeTypeId":1,"sizeNameId":1}],"sizePrice":"120","isSale":0},
         * {"courseSize":[{"sizeTypeId":1,"sizeNameId":1}],"sizePrice":"130","isSale":0}]
         */
        /*
            List<CourseSizeBo> lstSizePrice = JsonUtils.getList4Json(sizePriceInfo, CourseSizeBo.class);
            for (CourseSizeBo sizePrice : lstSizePrice){
            List<CourseSizeBo> lstSizePriceDetail = JsonUtils.getList4JsonArray(sizePrice.getCourseSize(),
                    CourseSizeBo.class);
            for (CourseSizeBo sizePriceDetail : lstSizePriceDetail){

            }
        }*/
        return "";
       /* return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加成功", "").toString();*/
    }

    /**
     * 修改课程信息
     */
    public String updateCourse(Course course){
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询课程是否存在
        Course courseInfo = courseDao.queryCourseByCourseId(course.getCourseId());
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在", "").toString();
        }
        courseInfo.setCourseTitle(course.getCourseTitle());
        courseInfo.setCourseName(course.getCourseName());
        courseInfo.setCoursePrice(course.getCoursePrice());
        courseInfo.setCourseDistrict(course.getCourseDistrict());
        courseInfo.setCourseImageUrl(course.getCourseImageUrl());
        courseInfo.setIntroduce(course.getIntroduce());
        courseDao.updateCourse(courseInfo);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }

    public List<Course> queryCourseList(){
        return courseDao.queryCourseList();
    }

    public Course queryCourseById(Integer courseId){
        return courseDao.queryCourseByCourseId(courseId);
    }

    public String updateCourseImageUrl(MultipartFile file){
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
        String urlMsg = SystemConfig.getString("course_image_url");
        urlMsg = MessageFormat.format(urlMsg, new Object[]{yyyyMMdd, fileName});
        String courseImageUrl = urlMsg.replace("/attached", SystemConfig.getString("img_file_root"));
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
        jo.put("courseImageUrl", courseImageUrl);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    public String updateCourseStatus(Integer courseId, Integer status){
        if(courseId == null || status == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("courseId", courseId);
        map.put("status", status);
        courseDao.updateCourseStatus(map);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }

    public String queryClientCourseList(){
        List<Course> lstCourse = courseDao.queryCourseList();
        if(CollectionUtils.isEmpty(lstCourse)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有课程数据", "").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("lstCourse", JsonUtils.getJsonString4JavaListDate(lstCourse, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryCourseDetail(Integer courseId){
        if(courseId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Course course = courseDao.queryUpCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在或已经下架", "").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("baseUrl", SystemConfig.getString("image_base_url"));
        jo.put("course", JsonUtils.getJsonObject4JavaPOJO(course, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryCourseSize(Integer courseId){
        if(courseId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        List<CourseSize> lstSize = courseSizeDao.queryCourseSize(courseId);
        if(CollectionUtils.isEmpty(lstSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有规格信息", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaListDate(lstSize, DateUtils.LONG_DATE_PATTERN)).toString();
    }
}
