package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.CourseStoreDao;
import com.urbanfit.apiserver.entity.CourseStore;
import com.urbanfit.apiserver.util.JsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2018/8/5.
 */
@Service("courseStoreService")
@Transactional
public class CourseStoreService {
    @Resource
    private CourseStoreDao courseStoreDao;

    public String queryStoreChoosedCourse(Integer storeId){
        if(storeId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        List<CourseStore> lstCourseStore = courseStoreDao.queryStoreChoosedCourse(storeId);
        if(CollectionUtils.isEmpty(lstCourseStore)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "没有课程信息", "").toString();
        }
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "查询成功", JsonUtils.
                getJsonString4JavaList(lstCourseStore)).toString();
    }
}
