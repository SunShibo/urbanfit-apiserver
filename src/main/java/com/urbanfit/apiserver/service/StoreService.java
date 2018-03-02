package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.StoreDao;
import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
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
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 根据门店名称查询是否添加过该门店
        Store storeName = storeDao.queryStoreByName(store.getStoreName());
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "您添加的门店已经存在")) ;
        }
        storeDao.addStore(store);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000", "添加门店信息成功")) ;
    }

    public String updateStore(Store store){
        if(store == null || (store != null && (store.getStoreId() == null || StringUtils.isEmpty(store.getStoreName())
                || StringUtils.isEmpty(store.getStoreDistrict()) || StringUtils.isEmpty(store.getStoreAddress())
                || StringUtils.isEmpty(store.getMobile()) || StringUtils.isEmpty(store.getContactName())))){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeName", store.getStoreName());
        map.put("storeId", store.getStoreId());
        Store storeName = storeDao.queryStoreByNameAndId(map);
        if(storeName != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "您添加的门店已经存在")) ;
        }
        // 修改门店信息
        storeDao.updateStore(store);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000000", "修改门店信息成功")) ;
    }
}
