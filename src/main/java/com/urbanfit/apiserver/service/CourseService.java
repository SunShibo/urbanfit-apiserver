package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.*;
import com.urbanfit.apiserver.entity.Course;
import com.urbanfit.apiserver.entity.CourseSize;
import com.urbanfit.apiserver.entity.CourseSizeDetail;
import com.urbanfit.apiserver.entity.CourseStore;
import com.urbanfit.apiserver.entity.bo.CourseSizeBo;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.*;
import com.urbanfit.apiserver.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.text.MessageFormat;
import java.util.*;

@Service("courseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseSizeDao courseSizeDao;
    @Autowired
    private CourseSizeDetailDao courseSizeDetailDao;
    @Autowired
    private CourseStoreDao courseStoreDao;
    @Resource
    private StoreDao storeDao;

    /**
     * 添加课程数据
     */
    public String addCourse(String courseName, String storeIds, String courseSizeInfo, String sizePriceInfo,
                            String introduce, Integer courseType, String courseImageUrl){
        if(StringUtils.isEmpty(courseName) || StringUtils.isEmpty(storeIds) || StringUtils.isEmpty(courseSizeInfo)
                || StringUtils.isEmpty(sizePriceInfo) || StringUtils.isEmpty(introduce) || courseType == null
                || StringUtils.isEmpty(courseImageUrl)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseType(courseType);
        course.setIntroduce(introduce);
        course.setStoreId("," + storeIds + ",");
        course.setCourseImageUrl(courseImageUrl);
        course.setCreateTime(new Date());
        // 添加课程信息
        courseDao.addCourse(course);
        // 添加课程规格
        List<CourseSizeBo> lstSize = JsonUtils.getList4Json(courseSizeInfo, CourseSizeBo.class);
        if(CollectionUtils.isEmpty(lstSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Double coursePrice = addCourseSize(lstSize, course, sizePriceInfo, storeIds);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("coursePrice", coursePrice);
        map.put("courseId", course.getCourseId());
        courseDao.updateCoursePrice(map);
        storeDao.updateStoreCourse();      // 修改门店课程信息
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加成功", "").toString();
    }

    /**
     * 修改课程信息
     */
    public String updateCourse(String courseName, String storeIds, String courseSizeInfo, String sizePriceInfo,
                               String introduce, Integer courseType, Integer courseId, String courseImageUrl){
        if(StringUtils.isEmpty(courseName) || StringUtils.isEmpty(storeIds) || StringUtils.isEmpty(courseSizeInfo)
                || StringUtils.isEmpty(sizePriceInfo) || StringUtils.isEmpty(introduce) || courseType == null
                || courseId == null || StringUtils.isEmpty(courseImageUrl)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 查询课程是否存在
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "课程不存在", "").toString();
        }
        // 删除相关数据
        courseSizeDao.deleteCourseSize(courseId);
        courseSizeDetailDao.deleteCourseSizeDetail(courseId);
        courseStoreDao.deleteCourseStore(courseId);
        // 添加课程规格
        List<CourseSizeBo> lstSize = JsonUtils.getList4Json(courseSizeInfo, CourseSizeBo.class);
        if(CollectionUtils.isEmpty(lstSize)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Double coursePrice = addCourseSize(lstSize, course, sizePriceInfo, storeIds);
        course.setCourseName(courseName);
        course.setCourseType(courseType);
        course.setIntroduce(introduce);
        course.setStoreId("," + storeIds + ",");
        course.setCoursePrice(coursePrice);
        course.setCourseImageUrl(courseImageUrl);
        courseDao.updateCourse(course);
        storeDao.updateStoreCourse();      // 修改门店课程信息
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }

    public PageObject<Course> queryCourseList(QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        PageObjectUtil page = new PageObjectUtil<Course>();
        return page.savePageObject(courseDao.queryCountCourse(map), courseDao.queryListCourse(map), queryInfo);
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
        List<CourseSize> lstSizeType = courseSizeDao.queryCourseSizeType(courseId);
        List<CourseSize> lstSizeName = courseSizeDao.queryCourseSizeName(courseId);
        List<CourseSizeDetail> lstSizeDetail = courseSizeDetailDao.queryCourseSizeDetail(courseId);
        if(CollectionUtils.isEmpty(lstSizeType) || CollectionUtils.isEmpty(lstSizeName) || CollectionUtils.
                isEmpty(lstSizeDetail)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有规格信息", "").toString();
        }
        JSONObject jo = new JSONObject();
        jo.put("lstSizeType", JsonUtils.getJsonString4JavaListDate(lstSizeType, DateUtils.LONG_DATE_PATTERN));
        jo.put("lstSizeName", JsonUtils.getJsonString4JavaListDate(lstSizeName, DateUtils.LONG_DATE_PATTERN));
        jo.put("lstSizeDetail", JsonUtils.getJsonString4JavaListDate(lstSizeDetail, DateUtils.LONG_DATE_PATTERN));
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    private String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    private double addCourseSize(List<CourseSizeBo> lstSize, Course course, String sizePriceInfo, String storeIds){
        Map<Integer, Integer> courseSizeMap = new HashMap<Integer, Integer>();
        for (CourseSizeBo size : lstSize){
            CourseSize courseSize = new CourseSize(size.getSizeTypeName(), null, course.getCourseId());
            courseSizeDao.addCourseSize(courseSize);
            // 添加数据
            List<CourseSizeBo> lstSizeName = JsonUtils.getList4JsonArray(size.getSizeNameInfo(), CourseSizeBo.class);
            for (CourseSizeBo sizeName : lstSizeName){
                CourseSize courseSizeName = new CourseSize(sizeName.getSizeName(), courseSize.getSizeId(),
                        course.getCourseId());
                courseSizeDao.addCourseSize(courseSizeName);        // 添加数据
                courseSizeMap.put(sizeName.getSizeNameId(), courseSizeName.getSizeId());
            }
        }
        // 添加课程规格价格
        List<CourseSizeBo> lstSizePrice = JsonUtils.getList4Json(sizePriceInfo, CourseSizeBo.class);
        List<CourseSizeDetail> lstSizeDetail = new ArrayList<CourseSizeDetail>();
        List<Double> lstCourseSizePrice = new ArrayList<Double>();
        for (CourseSizeBo sizePrice : lstSizePrice){
            String[] sizeIds = sizePrice.getCourseSize().split(",");
            List<Integer> lstSizeId = new ArrayList<Integer>();
            for (String sizeId : sizeIds){
                if(courseSizeMap.containsKey(Integer.parseInt(sizeId))){
                    lstSizeId.add(courseSizeMap.get(Integer.parseInt(sizeId)));
                }
            }
            CourseSizeDetail sizeDetail = new CourseSizeDetail(listToString(lstSizeId, ","), sizePrice.getSizePrice(),
                    sizePrice.getIsSale(), course.getCourseId());
            lstSizeDetail.add(sizeDetail);
            lstCourseSizePrice.add(sizePrice.getSizePrice());
        }
        // 规格价格排序
        Collections.sort(lstCourseSizePrice);
        courseSizeDetailDao.batchAddCourseSizeDetail(lstSizeDetail);
        // 添加课程俱乐部信息
        if(!StringUtils.isEmpty(storeIds)){
            List<CourseStore> lstCourseStore = new ArrayList<CourseStore>();
            String[] storeIdArr = storeIds.split(",");
            for (String storeId : storeIdArr){
                CourseStore courseStore = new CourseStore(course.getCourseId(), Integer.parseInt(storeId));
                lstCourseStore.add(courseStore);
            }
            courseStoreDao.batchAddCourseStore(lstCourseStore);
        }
        return lstCourseSizePrice.get(0);
    }

    public PageObject<Course> queryStoreCourseList(String courseName, Integer courseType, String courseIds,
                                             QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(courseName)){
            map.put("courseName", courseName);
        }
        if(courseType != null){
            map.put("courseType", courseType);
        }
        if(!StringUtils.isEmpty(courseIds)){
            map.put("courseIds", courseIds.split(","));
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil<Course> page = new PageObjectUtil<Course>();
        return page.savePageObject(courseDao.queryStoreCourseCount(map), courseDao.queryStoreCourseList(map),
                queryInfo);
    }
}
