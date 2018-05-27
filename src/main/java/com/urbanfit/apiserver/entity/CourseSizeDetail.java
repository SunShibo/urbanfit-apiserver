package com.urbanfit.apiserver.entity;

import java.util.Date;

/**
 * Created by Administrator on 2018/5/27.
 */
public class CourseSizeDetail {
    private Integer detailId;
    private String sizeDetail;
    private Double sizePrice;
    private int isSale;
    private Date createTime;
    private Integer courseId;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getSizeDetail() {
        return sizeDetail;
    }

    public void setSizeDetail(String sizeDetail) {
        this.sizeDetail = sizeDetail;
    }

    public Double getSizePrice() {
        return sizePrice;
    }

    public void setSizePrice(Double sizePrice) {
        this.sizePrice = sizePrice;
    }

    public int getIsSale() {
        return isSale;
    }

    public void setIsSale(int isSale) {
        this.isSale = isSale;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public CourseSizeDetail(){}

    public CourseSizeDetail(String sizeDetail, Double sizePrice, int isSale, Integer courseId) {
        this.sizeDetail = sizeDetail;
        this.sizePrice = sizePrice;
        this.isSale = isSale;
        this.courseId  = courseId;
    }
}
