package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.service.StoreService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/28.
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseCotroller{
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "storeService")
    private StoreService storeService;

    @RequestMapping( value = "/add" )
    public void addStore(HttpServletResponse response, Store store){
        String result = storeService.addStore(store);
        safeJsonPrint(response, result);
    }

    @RequestMapping(value = "/list")
    public void queryStoreList(){

    }

    @RequestMapping(value = "/update")
    public void updateStore(HttpServletResponse response, Store store){
        String result = storeService.updateStore(store);
        safeJsonPrint(response, result);
    }
}
