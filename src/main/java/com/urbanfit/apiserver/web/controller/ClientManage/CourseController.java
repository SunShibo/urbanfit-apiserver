package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.service.CourseService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/3/21.
 */
@Controller("ccourse")
@RequestMapping()
public class CourseController extends BaseCotroller{
    @Resource(name = "coachAuthService")
    private CourseService courseService;


}
