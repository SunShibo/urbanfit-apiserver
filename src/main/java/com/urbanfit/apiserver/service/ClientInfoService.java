package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.ClientInfoDao;
import com.urbanfit.apiserver.entity.ClientInfo;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/21.
 */
@Service("clientInfoService")
@Transactional
public class ClientInfoService {
    @Resource
    private ClientInfoDao clientInfoDao;

    /**
     * 注册用户
     */
    public String register(String mobile, String password, String confirmPassword){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 判断手机号码是否注册过
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo != null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "手机号码已经存在", "").toString();
        }
        if(!password.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 注册用户
        ClientInfo newClient = new ClientInfo();
        newClient.setMobile(mobile);
        newClient.setPassword(password);
        clientInfoDao.addClientInfo(newClient);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "注册成功", "").toString();
    }

    public String login(String mobile, String password){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        // 判断密码是否正确
        if(!password.equals(clientInfo.getPassword())){
            return JsonUtils.encapsulationJSON(2, "密码输入不正确", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "登录成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo, DateUtils.LONG_DATE_PATTERN)).toString();
    }

    public String updatePassword(String mobile, String newPassword, String confirmPassword){
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientInfoByMobile(mobile);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        if(!newPassword.equals(confirmPassword)){
            return JsonUtils.encapsulationJSON(2, "两次密码输入不正确", "").toString();
        }
        // 修改客户密码
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", mobile);
        map.put("password", newPassword);
        clientInfoDao.updatePassword(map);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "修改密码成功", "").toString();
    }

    public String queryClientInfo(Integer clientId){
        if(clientId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        ClientInfo clientInfo = clientInfoDao.queryClientById(clientId);
        if(clientInfo == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "用户不存在", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询信息成功", JsonUtils.
                getJsonString4JavaPOJO(clientInfo)).toString();
    }
}
