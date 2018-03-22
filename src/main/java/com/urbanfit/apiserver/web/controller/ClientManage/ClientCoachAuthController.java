package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.CoachAuthService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/21.
 */
@Controller()
@RequestMapping("/cauth")
public class ClientCoachAuthController extends BaseCotroller{
    @Resource(name = "coachAuthService")
    private CoachAuthService coachAuthService;

    /**
     * 查询教练认证信息
     */
    @RequestMapping("/query")
    public void queryCoachAuth(HttpServletResponse response, String coachName, String coachCardNum){
        String result = coachAuthService.queryCoachAuth(coachName, coachCardNum);
        safeJsonPrint(response, result);
    }
}
