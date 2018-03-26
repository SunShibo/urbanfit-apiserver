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

    public OrderMaster queryOderMaterDetail(String orderNum);

    public OrderMaster queryOrderMasterByOrderNum(String orderNum);

    public void updateOrderMasterStatus(String orderNum);
}
