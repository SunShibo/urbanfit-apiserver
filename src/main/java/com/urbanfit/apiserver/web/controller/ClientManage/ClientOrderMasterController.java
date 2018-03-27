package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.OrderMasterService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/27.
 */
@Controller
@RequestMapping("/corder")
public class ClientOrderMasterController extends BaseCotroller{
    @Resource
    private OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public void queryClientOrderMaster(HttpServletResponse response, Integer clientId, String orderNum,
                                       Integer status, Integer pageNo){
        String result = orderMasterService.queryClientOrderMaster(clientId, orderNum, status,
                getQueryInfo(pageNo, null));
        safeJsonPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryClientOrderMasterDetail(HttpServletResponse response, Integer clientId, String orderNum){
        String result = orderMasterService.queryClientOrderMasterDetail(clientId, orderNum);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/pay")
    public void addClientOrderMaster(HttpServletResponse response, String params){
        String result = orderMasterService.addClientOrderMaster(params);
        safeJsonPrint(response, result);
    }
}
