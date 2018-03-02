package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.dao.CoachAuthDao;
import com.urbanfit.apiserver.service.CoachAuthService;
import com.urbanfit.apiserver.util.StringUtils;
import com.urbanfit.apiserver.web.controller.LoginController;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/1.
 */
@Controller
@RequestMapping("/auth")
public class CoachAuthController extends BaseCotroller {
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "coachAuthService")
    private CoachAuthService coachAuthService;

    @RequestMapping("/add")
    public void addCoachAuth(HttpServletResponse response, String coachName, String coachCardNum){
        String result = coachAuthService.addCoachAuth(coachName, coachCardNum);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/delete")
    public void deleteCoachAuth(HttpServletResponse response, Integer authId){
        String result = coachAuthService.deleteCoachAuth(authId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/update")
    public void updateCoachAuth(HttpServletResponse response, Integer authId, String coachName, String coachCardNum){
        String result = coachAuthService.updateCoachAuth(authId, coachName, coachCardNum);
        safeJsonPrint(response, result);
    }
}
