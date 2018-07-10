package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.ClientApplyRefund;

/**
 * Created by yxw on 2018/7/9.
 */
public interface ClientApplyRefundDao {
    public String queryReasonByOrderNum(String orderNum);
    public void   updateAgree(String orderNum);
    public void updateAgainst(ClientApplyRefund clientApplyRefund);
    public ClientApplyRefund queryHandleDetail(String orderNum);
}
