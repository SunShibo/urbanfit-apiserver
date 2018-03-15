package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.service.ActivityMessageService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        return view;
    }

    @RequestMapping("toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/message/activity_message_add");
        return view;
    }

    @RequestMapping("delete")
    public void deleteActivityMessage(HttpServletResponse response, Integer messageId){
        String result = activityMessageService.deleteActivityMessage(messageId);
        safeJsonPrint(response, result);
    }
}
