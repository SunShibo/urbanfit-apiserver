package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Store;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/28.
 */
public interface StoreDao {
    /**
     * 添加门店信息
     */
    public void addStore(Store store);

    /**
     * 根据门店名称查询数据
     */
    public Store queryStoreByName(String storeName);

    /**
     * 修改门店信息
     */
    public void updateStore(Store store);

    /**
     * 根据门店名称、门店id查询门店数据
     */
    public Store queryStoreByNameAndId(Map<String, Object> map);
}
