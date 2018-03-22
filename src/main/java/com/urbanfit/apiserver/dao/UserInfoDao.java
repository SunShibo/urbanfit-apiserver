package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.UserInfo;

/**
 * Created by Administrator on 2018/3/22.
 */
public interface UserInfoDao {

    public UserInfo queryUserInfoByAccount(String account);
}
