package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Course;

/**
 * Created by Administrator on 2018/3/2.
 */
public interface CourseDao {

    /**
     * 根据课程类型查询课程
     */
    public Course queryCourseByType(Integer courseType);

    public void addCourse(Course course);

    public void updateCourse(Course course);

    public Course queryCourseByCourseId(Integer courseId);
}
