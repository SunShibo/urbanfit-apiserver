package com.urbanfit.apiserver.entity;

import java.util.Date;

/**
 * Created by yxw on 2018/7/9.
 */
public class ClientApplyRefund {
    private Integer applyId;
    private Integer clientId;
    private Integer userId;
    private int status;
    private String reason;
    private String orderNum;
    private Date handleTime;
    private Date applyTime;
    private  String againstReason;

    public String getAgainstReason() {
        return againstReason;
    }

    public void setAgainstReason(String againstReason) {
        this.againstReason = againstReason;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
}
