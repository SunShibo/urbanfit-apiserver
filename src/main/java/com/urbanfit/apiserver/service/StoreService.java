package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.StoreDao;
import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28.
 */
@Service("storeService")
@Transactional
public class StoreService {
    @Resource
    private StoreDao storeDao;

    public String addStore(Store store){
        if(store == null || (store != null && (StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                /*|| StringUtils.isEmpty(store.getMobile()) || StringUtils.isEmpty(store.getContactName())*/))
                || StringUtils.isEmpty(store.getIntroduce()) || StringUtils.isEmpty(store.getCourseIds())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        // 根据门店名称查询是否添加过该门店
        Store storeName = storeDao.queryStoreByName(store.getStoreName());
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        store.setCourseIds("," + store.getCourseIds() + ",");
        storeDao.addStore(store);
        // 修改课程门店信息
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加门店信息成功")) ;
    }

    public String updateStore(Store store){
        if(store == null || (store != null && (store.getStoreId() == null || StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                /*|| StringUtils.isEmpty(store.getMobile()) || StringUtils.isEmpty(store.getContactName())*/))
                || StringUtils.isEmpty(store.getIntroduce()) || StringUtils.isEmpty(store.getCourseIds())){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeName", store.getStoreName());
        map.put("storeId", store.getStoreId());
        Store storeName = storeDao.queryStoreByNameAndId(map);
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        store.setCourseIds("," + store.getCourseIds() + ",");
        // 修改门店信息
        storeDao.updateStore(store);
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

    public String queryCourseChoosedStore(String storeIds){
        if(StringUtils.isEmpty(storeIds)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(storeIds)){
            map.put("storeIds", storeIds.split(","));
        }
        List<Store> lstStore = storeDao.queryCourseChooseStoreList(map);
        if(CollectionUtils.isEmpty(lstStore)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有俱乐部信息", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaListDate(lstStore, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    private void updateStoreCourse(Integer storeId, String courseIds){
        // 查询课程门店信息
        // 如果课程已经包含该门店，不做操作
        // 如果课程没有包含该门店，添加数据
    }
}
