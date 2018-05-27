package com.urbanfit.apiserver.entity;

import com.urbanfit.apiserver.common.base.BaseModel;
import java.util.Date;

public class CourseSize extends BaseModel{
    private Integer sizeId;
    private String sizeName;
    private Integer parentId;
    private Integer courseId;
    private Date createTime;

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public CourseSize( String sizeName, Integer parentId, Integer courseId) {
        this.sizeName = sizeName;
        this.parentId = parentId;
        this.courseId = courseId;
    }

    public CourseSize(Integer sizeId, String sizeName, Integer parentId, Integer courseId) {
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        this.parentId = parentId;
        this.courseId = courseId;
    }
}
