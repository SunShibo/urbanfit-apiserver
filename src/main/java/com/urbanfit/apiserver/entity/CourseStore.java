package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

public class CourseStore extends BaseModel{
    private Integer csId;
    private Integer courseId;
    private Integer storeId;

    private String courseName;

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public CourseStore() {}

    public CourseStore(Integer courseId, Integer storeId) {
        this.courseId = courseId;
        this.storeId = storeId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
