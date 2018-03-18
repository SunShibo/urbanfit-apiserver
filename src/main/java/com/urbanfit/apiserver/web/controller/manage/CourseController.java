package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Course;
import com.urbanfit.apiserver.service.CourseService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangyubo on 2018/3/17.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseCotroller{
    @Resource(name = "courseService")
    private CourseService courseService;

    @RequestMapping("/list")
    public ModelAndView queryCoachList(){
        List<Course> lstCourse = courseService.queryCourseList();
        ModelAndView view = new ModelAndView();
        view.setViewName("/course/course_list");
        view.addObject("lstCourse", lstCourse);
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer courseId){
        Course course = courseService.queryCourseById(courseId);
        ModelAndView view = new ModelAndView();
        view.setViewName("/course/course_update");
        view.addObject("course", course);
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("/update")
    public void updateCourse(HttpServletResponse response, Integer courseId, String introduce){
        String result = courseService.updateCourse(courseId, introduce);
        safeJsonPrint(response, result);
    }
}
