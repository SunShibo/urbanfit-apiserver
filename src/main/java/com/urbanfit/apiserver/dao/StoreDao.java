package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Store;
import java.util.List;
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

    public Store queryStoreById(Integer storeId);

    public List<Store> queryStoreList(Map<String, Object> map);

    public int queryStoreCount(Map<String, Object> map);

    public void updateStoreStatus(Integer storeId);

    public int queryClientStoreCount(Map<String, Object> map);

    public List<Store> queryClientStoreList(Map<String, Object> map);
}
