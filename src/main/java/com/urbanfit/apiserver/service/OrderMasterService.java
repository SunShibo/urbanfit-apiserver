package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.OrderMasterDao;
import com.urbanfit.apiserver.entity.OrderMaster;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/25.
 */
@Service("orderMasterService")
@Transactional
public class OrderMasterService {
    @Resource
    private OrderMasterDao orderMasterDao;

    public PageObject<OrderMaster> queryOrderMasterList(String orderInfo, String provice, String city,
                                                        String district, Integer status, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(orderInfo)) {
            map.put("orderInfo", orderInfo);
        }
        if(!StringUtils.isEmpty(provice)){
            map.put("provice", provice);
        }
        if(!StringUtils.isEmpty(city)){
            map.put("city", city);
        }
        if(!StringUtils.isEmpty(district)){
            map.put("district", district);
        }
        if(status != null){
            map.put("status", status);
        }
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        PageObjectUtil<OrderMaster> page = new PageObjectUtil<OrderMaster>();
        return page.savePageObject(orderMasterDao.queryOrderMasterCount(map), orderMasterDao.
                queryOrderMasterList(map), queryInfo);
    }

    public OrderMaster queryOderMaterDetail(String orderNum){
        return orderMasterDao.queryOderMaterDetail(orderNum);
    }

    public String updateOrderMasterStatus(String orderNum){
        if(StringUtils.isEmpty(orderNum)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        OrderMaster orderMaster = orderMasterDao.queryOrderMasterByOrderNum(orderNum);
        if(orderMaster == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单不存在", "").toString();
        }
        if(orderMaster.getStatus() == OrderMaster.STATUS_WAITING_PAY){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单待支付", "").toString();
        }
        if(orderMaster.getStatus() == OrderMaster.STATUS_REFUND){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "订单状态已经为已退款", "").toString();
        }
        orderMasterDao.updateOrderMasterStatus(orderNum);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改成功", "").toString();
    }
}
