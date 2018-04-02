package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Module;
import com.urbanfit.apiserver.service.ModuleService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/4/2.
 */
@Controller
@RequestMapping("/module")
public class ModuleController extends BaseCotroller{
    @Resource
    private ModuleService moduleService;

    @RequestMapping("/list")
    public ModelAndView queryModule(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/module/module_list");
        view.addObject("lstModule", moduleService.queryModule());
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer moduleId, int type){
        ModelAndView view = new ModelAndView();
        if(type == Module.TYPE_HOME_PAGE){
            view.setViewName("/module/home_page_update");
            view.addObject("moduleId", moduleId);
        }else if(type == Module.TYPE_MESSAGE_PAGE){
            view.setViewName("/module/message_page_update");
            view.addObject("module", moduleService.queryModuleById(moduleId));
        }
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("/update")
    public void updateModule(HttpServletResponse response, Module module){
        String result = moduleService.updateModule(module);
        safeTextPrint(response, result);
    }

    @RequestMapping("/updateStatus")
    public ModelAndView updateModuleStatus(Integer moduleId, int status){
        moduleService.updateModuleStatus(moduleId, status);
        ModelAndView view = new ModelAndView();
        view.setViewName("/module/module_list");
        view.addObject("lstModule", moduleService.queryModule());
        return view;
    }

    @RequestMapping("/uploadImage")
    public void uploadImageUrl(HttpServletResponse response, @RequestParam("myFile") MultipartFile file){
        String result = moduleService.uploadImageUrl(file);
        safeTextPrint(response, result);
    }

    @RequestMapping("/queryById")
    public void queryModuleByModuleId(HttpServletResponse response, Integer moduleId){
        String result = moduleService.queryModuleByModuleId(moduleId);
        safeTextPrint(response, result);
    }
}
