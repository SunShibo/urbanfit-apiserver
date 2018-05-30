package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;

public class CourseStore extends BaseModel{
    private Integer csId;
    private Integer courseId;
    private Integer storeId;

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
}
