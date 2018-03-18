package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.ActivityMessage;
import com.urbanfit.apiserver.service.ActivityMessageService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangyubo on 2018/3/15.
 */
@Controller
@RequestMapping("/message")
public class ActivityMessageController extends BaseCotroller{
    @Autowired
    private ActivityMessageService activityMessageService;

    @RequestMapping("/list")
    public ModelAndView queryActivityMessageList(Integer pageNo, Integer pageSize, String title){
        pager = activityMessageService.queryActivityMessageList(title, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();

        view.setViewName("/message/activity_message_list");
        view.addObject("lstActivityMessage", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("title", title);
        return view;
    }

    @RequestMapping("toUpdate")
    public ModelAndView redirectUpdatePage(Integer messageId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message/activity_message_update");
        view.addObject("activityMessage", activityMessageService.queryActivityMessageById(messageId));
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message/activity_message_add");
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("delete")
    public void deleteActivityMessage(HttpServletResponse response, Integer messageId){
        String result = activityMessageService.deleteActivityMessage(messageId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/add")
    public void addActivityMessage (HttpServletResponse response, ActivityMessage activityMessage){
        String result = activityMessageService.addActivityMessage(activityMessage);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/update")
    public void updateActivityMessage (HttpServletResponse response, ActivityMessage activityMessage){
        String result = activityMessageService.updateActivityMessage(activityMessage);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/uploadThumbnails")
    public void uploadThumbnails(HttpServletResponse response, @RequestParam("myFile") MultipartFile file){
        String result = activityMessageService.uploadThumbnails(file);
        safeJsonPrint(response, result);
    }
}
