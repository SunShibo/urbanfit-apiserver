package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.StoreService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/cstore")
public class ClientStoreController extends BaseCotroller{
    @Resource(name = "storeService")
    private StoreService storeService;

    @RequestMapping("/list")
    public void queryStoreList(HttpServletResponse response, String provice, String city, String district,
                               Integer pageNo, Integer pageSize){
        String result = storeService.queryStoreList(provice, city, district, getQueryInfo(pageNo, pageSize));
        safeJsonPrint(response, result);
    }
}
