package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.StoreDao;
import com.urbanfit.apiserver.entity.Store;
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
                || StringUtils.isEmpty(store.getMobile()) || StringUtils.isEmpty(store.getContactName())))){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        // 根据门店名称查询是否添加过该门店
        Store storeName = storeDao.queryStoreByName(store.getStoreName());
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        storeDao.addStore(store);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "添加门店信息成功")) ;
    }

    public String updateStore(Store store){
        if(store == null || (store != null && (store.getStoreId() == null || StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                || StringUtils.isEmpty(store.getMobile()) || StringUtils.isEmpty(store.getContactName())))){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "参数有误")) ;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeName", store.getStoreName());
        map.put("storeId", store.getStoreId());
        Store storeName = storeDao.queryStoreByNameAndId(map);
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0", "您添加的门店已经存在")) ;
        }
        // 修改门店信息
        storeDao.updateStore(store);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1", "修改门店信息成功")) ;
    }

    public Store queryStoreById(Integer storeId){
        return storeDao.queryStoreById(storeId);
    }

    public PageObject<Store> queryStoreList(String storeName, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(storeName)){
            map.put("storeName", storeName);
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
}
