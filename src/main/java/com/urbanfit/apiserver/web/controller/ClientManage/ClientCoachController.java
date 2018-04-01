package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.CoachService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/ccoach")
public class ClientCoachController extends BaseCotroller{
    @Resource(name = "coachService")
    private CoachService coachService;

    @RequestMapping("list")
    public void queryCoachList(HttpServletResponse response, Integer pageNo, Integer pageSize){
        String result = coachService.queryCoachList(getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }
}
