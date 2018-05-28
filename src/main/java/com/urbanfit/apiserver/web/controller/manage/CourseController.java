package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import com.urbanfit.apiserver.entity.Course;
import com.urbanfit.apiserver.service.CourseService;
import com.urbanfit.apiserver.util.StringUtils;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wangyubo on 2018/3/17.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseCotroller {
    @Resource(name = "courseService")
    private CourseService courseService;

    @RequestMapping("/list")
    public ModelAndView queryCoachList() {
        List<Course> lstCourse = courseService.queryCourseList();
        ModelAndView view = new ModelAndView();
        view.setViewName("/course/course_list");
        view.addObject("lstCourse", lstCourse);
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView redirectUpdatePage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/course/course_add");
        return view;
    }

    @RequestMapping("/toUpdate")
    public ModelAndView redirectUpdatePage(Integer courseId) {
        Course course = courseService.queryCourseById(courseId);
        ModelAndView view = new ModelAndView();
        view.setViewName("/course/course_update");
        if(!StringUtils.isEmpty(course.getStoreId())){
            course.setStoreId(course.getStoreId().substring(1, course.getStoreId().length() - 1));
        }
        view.addObject("course", course);
        view.addObject("baseUrl", SystemConfig.getString("image_base_url"));
        sput("base_image", SystemConfig.getString("image_base_url"));
        return view;
    }

    @RequestMapping("/update")
    public void updateCourse(String courseName, String storeIds, String courseSizeInfo, String sizePriceInfo,
                             String introduce, Integer courseType, Integer courseId, HttpServletResponse response) {
        String result = courseService.updateCourse(courseName, storeIds, courseSizeInfo, sizePriceInfo,
                introduce, courseType, courseId);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/uploadImage")
    public void updateCourseImageUrl(HttpServletResponse response, @RequestParam("myFile") MultipartFile file) {
        String result = courseService.updateCourseImageUrl(file);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/updateStatus")
    public void updateCourseStatus(HttpServletResponse response, Integer courseId, Integer status){
        String result = courseService.updateCourseStatus(courseId, status);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/courseSize")
    public void queryCourseSize(Integer courseId, HttpServletResponse response){
        String result = courseService.queryCourseSize(courseId);
        safeTextPrint(response, result);
    }

    @RequestMapping("/addCourse")
    public void addCourse(String courseName, String storeIds, String courseSizeInfo, String sizePriceInfo,
                          String introduce, Integer courseType, HttpServletResponse response){
        String result = courseService.addCourse(courseName, storeIds, courseSizeInfo, sizePriceInfo, introduce,
                courseType);
        safeTextPrint(response, result);
    }
}