package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2018/3/21.
 */
public class ClientInfo extends BaseModel{
    private Integer clientId;
    private String mobile;
    private String name;
    private String password;
    private int status;
    private Date createTime;


    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
