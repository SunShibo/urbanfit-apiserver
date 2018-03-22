package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.ActivityMessageService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/cmessage")
public class ClientActivityMessageController extends BaseCotroller{

    @Resource
    private ActivityMessageService activityMessageService;

    @RequestMapping("/list")
    public void queryActivityMessage(HttpServletResponse response, Integer pageNo, Integer pageSize){
        String result = activityMessageService.queryActivityMessageList(getQueryInfo(pageNo, pageSize));
        safeJsonPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryActivityMessageDetail(HttpServletResponse response, Integer messageId){
        String result = activityMessageService.queryActivityMessageDetail(messageId);
        safeJsonPrint(response, result);
    }
}
