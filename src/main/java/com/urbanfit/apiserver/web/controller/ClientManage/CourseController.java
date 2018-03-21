package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.CourseService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;


@Controller()
@RequestMapping("/ccourse")
public class CourseController extends BaseCotroller{
    @Resource(name = "courseService")
    private CourseService courseService;


}
