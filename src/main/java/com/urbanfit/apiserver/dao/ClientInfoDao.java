package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.ClientInfo;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/21.
 */
public interface ClientInfoDao {

    public void addClientInfo(ClientInfo clientInfo);

    public ClientInfo queryClientInfoByMobile(String mobile);

    public void updatePassword(Map<String, Object> map);

    public ClientInfo queryClientById(Integer clientId);
}
