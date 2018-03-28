package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.OrderMaster;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/25.
 */
public interface OrderMasterDao {

    public int queryOrderMasterCount(Map<String, Object> map);

    public List<OrderMaster> queryOrderMasterList(Map<String, Object> map);

    public OrderMaster queryOrderMaterDetail(String orderNum);

    public OrderMaster queryOrderMasterByOrderNum(String orderNum);

    public void updateOrderMasterStatus(String orderNum);

    public int queryClientOrderMasterCount(Map<String, Object> map);

    public List<OrderMaster> queryClientOrderMasterList(Map<String, Object> map);

    public OrderMaster queryClientOrderMaterDetail(Map<String, Object> map);

    public void addOrderMaster(OrderMaster orderMaster);

    public void updateOrderMaster(Map<String, Object> map);
}
