package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.OrderMasterDao;
import com.urbanfit.apiserver.entity.OrderMaster;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
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
}
