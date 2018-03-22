package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.UserInfoDao;
import com.urbanfit.apiserver.entity.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service("userInfoService")
@Transactional
public class UserInfoService {
    @Resource
    private UserInfoDao userInfoDao;

    public UserInfo queryUserInfoByAccount(String account){
        UserInfo userInfo = userInfoDao.queryUserInfoByAccount(account);
        if(userInfo == null){
            return null;
        }
        userInfo.setUuid(UUID.randomUUID().toString());
        return userInfo;
    }
}
