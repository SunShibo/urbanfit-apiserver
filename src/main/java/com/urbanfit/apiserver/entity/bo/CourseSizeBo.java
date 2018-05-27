package com.urbanfit.apiserver.entity.bo;

import net.sf.json.JSONArray;

/**
 * Created by Administrator on 2018/5/27.
 */
public class CourseSizeBo {
    private Integer sizeTypeId;
    private String sizeTypeName;
    private JSONArray sizeNameInfo;
    private Integer sizeNameId;
    private String sizeName;
    private JSONArray courseSize;
    private Double sizePrice;
    private int isSale;

    public Integer getSizeTypeId() {
        return sizeTypeId;
    }

    public void setSizeTypeId(Integer sizeTypeId) {
        this.sizeTypeId = sizeTypeId;
    }

    public String getSizeTypeName() {
        return sizeTypeName;
    }

    public void setSizeTypeName(String sizeTypeName) {
        this.sizeTypeName = sizeTypeName;
    }

    public JSONArray getSizeNameInfo() {
        return sizeNameInfo;
    }

    public void setSizeNameInfo(JSONArray sizeNameInfo) {
        this.sizeNameInfo = sizeNameInfo;
    }

    public Integer getSizeNameId() {
        return sizeNameId;
    }

    public void setSizeNameId(Integer sizeNameId) {
        this.sizeNameId = sizeNameId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
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

    public JSONArray getCourseSize() {
        return courseSize;
    }

    public void setCourseSize(JSONArray courseSize) {
        this.courseSize = courseSize;
    }
}
