package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.CourseService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Controller()
@RequestMapping("/ccourse")
public class ClientCourseController extends BaseCotroller{
    @Resource(name = "courseService")
    private CourseService courseService;

    @RequestMapping("/list")
    public void queryCourseList(HttpServletResponse response){
        String result = courseService.queryClientCourseList();
        safeTextPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryCourseDetail(HttpServletResponse response, Integer courseId){
        String result = courseService.queryCourseDetail(courseId);
        safeTextPrint(response, result);
    }
}
