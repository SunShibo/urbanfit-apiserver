package com.urbanfit.apiserver.web.controller.manage;
import com.urbanfit.apiserver.service.CourseStoreService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/8/5.
 */
@Controller
@RequestMapping("/courseStore")
public class CourseStoreController extends BaseCotroller{
    @Resource
    private CourseStoreService courseStoreService;

    @RequestMapping("/storeChoosedCourse")
    public void queryStoreChoosedCourse(HttpServletResponse response, Integer storeId){
        String result = courseStoreService.queryStoreChoosedCourse(storeId);
        safeTextPrint(response, result);
    }
}
