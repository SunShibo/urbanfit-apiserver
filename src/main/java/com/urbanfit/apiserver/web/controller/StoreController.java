package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.service.StoreService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/add" )
    public void addStore(HttpServletResponse response, Store store){
        String result = storeService.addStore(store);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/list")
    public ModelAndView queryStoreList(Integer pageNo, Integer pageSize, String storeName){
        pager = storeService.queryStoreList(storeName, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();

        view.setViewName("/store/store_list");
        view.addObject("lstStore", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("storeName", storeName);
        return view;
    }

    @RequestMapping("/update")
    public void updateStore(HttpServletResponse response, Store store){
        String result = storeService.updateStore(store);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/store/store_add");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer storeId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/store/store_update");
        view.addObject("store", storeService.queryStoreById(storeId));
        return view;
    }

    @RequestMapping("/delete")
    public void deleteStore(HttpServletResponse response, Integer storeId){
        String result = storeService.deleteStore(storeId);
        safeJsonPrint(response, result);
    }
}
