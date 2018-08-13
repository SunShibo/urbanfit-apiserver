package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.dao.CourseDao;
import com.urbanfit.apiserver.dao.CourseStoreDao;
import com.urbanfit.apiserver.dao.StoreDao;
import com.urbanfit.apiserver.entity.CourseStore;
import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
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
 * Created by Administrator on 2018/2/28.
 */
@Service("storeService")
@Transactional
public class StoreService {
    @Resource
    private StoreDao storeDao;
    @Resource
    private CourseStoreDao courseStoreDao;
    @Resource
    private CourseDao courseDao;

    public String addStore(Store store){
        if(store == null || (store != null && (StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                || StringUtils.isEmpty(store.getIntroduce()) || StringUtils.isEmpty(store.getCourseIds())))){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        // 根据门店名称查询是否添加过该门店
        Store storeName = storeDao.queryStoreByName(store.getStoreName());
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        String courseIds = store.getCourseIds();
        store.setCourseIds("," + store.getCourseIds() + ",");
        storeDao.addStore(store);
        // 添加课程门店信息
        updateStoreCourse(store.getStoreId(), courseIds);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加门店信息成功")) ;
    }

    public String updateStore(Store store){
        if(store == null || (store != null && (store.getStoreId() == null || StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                || StringUtils.isEmpty(store.getIntroduce()) || StringUtils.isEmpty(store.getCourseIds())))){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeName", store.getStoreName());
        map.put("storeId", store.getStoreId());
        Store storeName = storeDao.queryStoreByNameAndId(map);
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        String courseIds = store.getCourseIds();
        store.setCourseIds("," + store.getCourseIds() + ",");
        // 修改门店信息
        storeDao.updateStore(store);
        updateStoreCourse(store.getStoreId(), courseIds);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "修改门店信息成功")) ;
    }

    public Store queryStoreById(Integer storeId){
        return storeDao.queryStoreById(storeId);
    }

    public PageObject<Store> queryStoreList(String storeName, QueryInfo queryInfo, String storeIds){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(storeName)){
            map.put("storeName", storeName);
        }
        if(!StringUtils.isEmpty(storeIds)){
            map.put("storeIds", storeIds.split(","));
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil page = new PageObjectUtil<Store>();
        return page.savePageObject(storeDao.queryStoreCount(map), storeDao.queryStoreList(map), queryInfo);
    }

    public String deleteStore(Integer storeId){
        if(storeId == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")).toString();
        }
        // 根据id查询数据
        Store store = storeDao.queryStoreById(storeId);
        if(store == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您要删除的数据不存在")).toString();
        }
        // 删除数据
        storeDao.updateStoreStatus(storeId);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "删除数据成功")).toString();
    }

    public String queryStoreList(String provice, String city, String district, QueryInfo queryInfo){
        if(queryInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageOffset", queryInfo.getPageOffset());
        map.put("pageSize", queryInfo.getPageSize());
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", "district");
        }
        PageObjectUtil page = new PageObjectUtil<Store>();
        PageObject<Store> pager = page.savePageObject(storeDao.queryClientStoreCount(map),
                storeDao.queryClientStoreList(map), queryInfo);
        JSONObject jo = new JSONObject();
        jo.put("lstStore", JsonUtils.getJsonString4JavaListDate(pager.getDatas(), DateUtils.LONG_DATE_PATTERN));
        jo.put("totalRecord", pager.getTotalRecord());
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", jo.toString()).toString();
    }

    public String queryCourseChoosedStore(String storeIds) {
        if (StringUtils.isEmpty(storeIds)) {
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(storeIds)) {
            map.put("storeIds", storeIds.split(","));
        }
        List<Store> lstStore = storeDao.queryCourseChooseStoreList(map);
        if (CollectionUtils.isEmpty(lstStore)) {
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有俱乐部信息", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaListDate(lstStore, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public String updateStoreImageUrl(MultipartFile file){
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
        String urlMsg = SystemConfig.getString("store_image_url");
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
        jo.put("storeImageUrl", courseImageUrl);

        JSONObject resultJo = new JSONObject();
        resultJo.put("code", "1");
        resultJo.put("message", "success");
        resultJo.put("data", jo.toString());
        return resultJo.toString();
    }

    private void updateStoreCourse(Integer storeId, String courseIds){
        // 删除门店课程数据
        courseStoreDao.deleteCourseStoreByStoreId(storeId);
        // 添加门店课程数据
        String[] courseIdArr = courseIds.split(",");
        List<CourseStore> lstCourseStore = new ArrayList<CourseStore>();
        for (String courseIdStr : courseIdArr) {
            Integer courseId = Integer.parseInt(courseIdStr);
            CourseStore courseStore = new CourseStore(courseId, storeId);
            lstCourseStore.add(courseStore);
        }
        if(!CollectionUtils.isEmpty(lstCourseStore)) {
            courseStoreDao.batchAddCourseStore(lstCourseStore);
            // 修改课程绑定的门店数据
            courseDao.updateCourseStore();
        }
    }
}
