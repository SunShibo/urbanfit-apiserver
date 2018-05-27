package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.CourseSize;

import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */
public interface CourseSizeDao {
    public List<CourseSize> queryCourseSize(Integer courseId);

    public void addCourseSize(CourseSize courseSize);
}
