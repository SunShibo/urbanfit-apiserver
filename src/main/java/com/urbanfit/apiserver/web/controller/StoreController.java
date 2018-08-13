package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Store;
import com.urbanfit.apiserver.service.StoreService;
import com.urbanfit.apiserver.util.StringUtils;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
        pager = storeService.queryStoreList(storeName, getQueryInfo(pageNo, pageSize), null);
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
        Store store = storeService.queryStoreById(storeId);
        if(!StringUtils.isEmpty(store.getCourseIds())){
            store.setCourseIds(store.getCourseIds().substring(1, store.getCourseIds().length() - 1));
        }
        ModelAndView view = new ModelAndView();
        view.setViewName("/store/store_update");
        view.addObject("store", store);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("/delete")
    public void deleteStore(HttpServletResponse response, Integer storeId){
        String result = storeService.deleteStore(storeId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/courseStoreList")
    public ModelAndView queryCourseChooseStore(String storeName, String storeIds, Integer pageNo, Integer pageSize){
        pager = storeService.queryStoreList(storeName, getQueryInfo(pageNo, pageSize), storeIds);
        ModelAndView view = new ModelAndView();
        view.setViewName("/store/course_choose_store");
        view.addObject("lstStore", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("storeName", storeName);
        view.addObject("storeIds", storeIds);
        return view;
    }

    @RequestMapping("/courseChooseStore")
    public void queryCourseChoosedStore(String storeIds, HttpServletResponse response){
        String result = storeService.queryCourseChoosedStore(storeIds);
        safeTextPrint(response, result);
    }

    @RequestMapping("/uploadImage")
    public void updateStoreImageUrl(HttpServletResponse response, @RequestParam("myFile") MultipartFile file) {
        String result = storeService.updateStoreImageUrl(file);
        safeJsonPrint(response, result);
    }
}
