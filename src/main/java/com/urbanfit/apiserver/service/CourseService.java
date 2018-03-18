package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.dao.CourseDao;
import com.urbanfit.apiserver.entity.Course;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */
@Service("courseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 添加课程数据
     */
    public String addCourse(Integer courseType, String introduce){
        if(courseType == null || StringUtils.isEmpty(introduce)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查询类型是否添加过
        Course course = courseDao.queryCourseByType(courseType);
        if(course != null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "课程类型已经存在")) ;
        }
        Course courseseInfo = new Course();
        courseseInfo.setCourseType(courseType);
        courseseInfo.setIntroduce(introduce);
        courseDao.addCourse(courseseInfo);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "添加课程成功")) ;
    }

    /**
     * 修改课程信息
     */
    public String updateCourse(Integer courseId, String introduce){
        if(courseId == null || StringUtils.isEmpty(introduce)){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数有误")) ;
        }
        // 查询课程是否存在
        Course course = courseDao.queryCourseByCourseId(courseId);
        if(course == null){
            return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "课程不存在")) ;
        }
        course.setIntroduce(introduce);
        courseDao.updateCourse(course);
        return JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "修改课程成功")) ;
    }

    public List<Course> queryCourseList(){
        return courseDao.queryCourseList();
    }

    public Course queryCourseById(Integer courseId){
        return courseDao.queryCourseByCourseId(courseId);
    }
}
