package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.common.constants.SysConstants;
import com.urbanfit.apiserver.entity.UserInfo;
import com.urbanfit.apiserver.service.UserInfoService;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.MD5Util;
import com.urbanfit.apiserver.util.StringUtils;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseCotroller{
    @Resource(name = "userInfoService")
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public void login(HttpServletResponse response, String account, String password){
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(password)){
            safeJsonPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR,
                    "参数有误", "").toString());
            return ;
        }
        UserInfo userInfo = userInfoService.queryUserInfoByAccount(account);
        if(userInfo == null){
            safeJsonPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL,
                    "账号不存在", "").toString());
            return ;
        }
        System.out.println(MD5Util.digest(password));
        if(!MD5Util.digest(password).equals(userInfo.getPassword())){
            safeJsonPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL,
                    "密码输入有误", "").toString());
            return ;
        }

        super.putLoginUser(userInfo.getUuid(), userInfo);    // 保存到缓存
        super.setCookie(response , SysConstants.CURRENT_LOGIN_ID, userInfo.getUuid(), SysConstants.SEVEN_DAY_TIME);
        safeJsonPrint(response, JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "登录成功",
                JsonUtils.getJsonString4JavaPOJO(userInfo, DateUtils.LONG_DATE_PATTERN)).toString());
        return ;
    }
}
