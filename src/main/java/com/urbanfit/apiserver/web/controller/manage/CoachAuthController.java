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
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/list")
    public ModelAndView queryCoachAuthList(Integer pageNo, Integer pageSize, String authInfo){
        pager = coachAuthService.queryCoachAuthList(authInfo, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();

        view.setViewName("/coach/coach_auth_list");
        view.addObject("lstCoachAuth", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("authInfo", authInfo);
        return view;
    }

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

    @RequestMapping("/toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coach/coach_auth_add");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer authId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coach/coach_auth_update");
        // 根据id查询数据
        view.addObject("coachAuth", coachAuthService.queryCoachAuthById(authId));
        return view;
    }
}
