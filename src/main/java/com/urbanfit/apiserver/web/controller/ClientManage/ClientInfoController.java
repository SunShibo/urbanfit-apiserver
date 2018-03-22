package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.ClientInfoService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/21.
 */
@Controller
@RequestMapping("/client")
public class ClientInfoController extends BaseCotroller{

    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;

    /**
     * 客户注册
     */
    @RequestMapping( value = "/register" )
    public void register(HttpServletResponse response, String mobile, String password, String confirmPassword){
        String result = clientInfoService.register(mobile, password, confirmPassword);
        safeJsonPrint(response, result);
    }

    /**
     * 客户登录
     */
    @RequestMapping(value = "/login")
    public void login(HttpServletResponse response, String mobile, String password){
        String result = clientInfoService.login(mobile, password);
        safeJsonPrint(response, result);
    }

    /**
     * 重置密码
     */
    @RequestMapping(value = "/password")
    public void updatePassword(HttpServletResponse response, String mobile, String newPassword,
                               String confirmPassword){
        String result = clientInfoService.updatePassword(mobile, newPassword, confirmPassword);
        safeJsonPrint(response, result);
    }
}
