package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.ModuleService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/4.
 */
@Controller
@RequestMapping("/cmodule")
public class ClientModuleController extends BaseCotroller{
    @Resource
    private ModuleService moduleService;

    @RequestMapping("/list")
    public void queryModuleList(HttpServletResponse response, Integer type){
        String result = moduleService.queryModuleList(type);
        safeTextPrint(response, result);
    }
}
