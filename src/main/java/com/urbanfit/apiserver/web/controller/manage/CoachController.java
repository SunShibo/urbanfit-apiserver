package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Coach;
import com.urbanfit.apiserver.service.CoachService;
import com.urbanfit.apiserver.web.controller.LoginController;
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
 * Created by wangyubo on 2018/3/15.
 */
@Controller
@RequestMapping("/coach")
public class CoachController extends BaseCotroller{
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource(name = "coachService")
    private CoachService coachService;

    @RequestMapping("/list")
    public ModelAndView queryCoachList(Integer pageNo, Integer pageSize, String coachName){
        pager = coachService.queryCoachList(coachName, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();

        view.setViewName("/coach/coach_list");
        view.addObject("lstCoach", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("coachName", coachName);
        return view;
    }

    @RequestMapping("toUpdate")
    public ModelAndView redirectUpdatePage(Integer coachId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coach/coach_update");
        view.addObject("coach", coachService.queryCoachById(coachId));
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("toAdd")
    public ModelAndView redirectAddPage(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coach/coach_add");
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("delete")
    public void deleteActivityMessage(HttpServletResponse response, Integer coachId){
        String result = coachService.deleteCoach(coachId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/uploadHeadPortrait")
    public void uploadHeadPortrait(HttpServletResponse response, @RequestParam("myFile") MultipartFile file){
        String result = coachService.uploadHeadPortrait(file);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/add")
    public void addCoach(HttpServletResponse response, Coach coach){
        String result = coachService.addCoach(coach);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/update")
    public void updateCoach(HttpServletResponse response, Coach coach){
        String result = coachService.updateCoach(coach);
        safeJsonPrint(response, result);
    }
}
