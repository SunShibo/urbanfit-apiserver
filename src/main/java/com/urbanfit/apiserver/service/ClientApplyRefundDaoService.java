package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.ClientApplyRefundDao;
import com.urbanfit.apiserver.entity.ClientApplyRefund;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yxw on 2018/7/9.
 */
@Service
public class ClientApplyRefundDaoService {
    @Resource
    private ClientApplyRefundDao clientApplyRefundDao;
    public String queryReason(String orderNum){
        return  clientApplyRefundDao.queryReasonByOrderNum(orderNum);
    }
    public void updateAgree(String orderNum){
        clientApplyRefundDao.updateAgree(orderNum);
    }

}
