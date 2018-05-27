package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.CourseSizeDetail;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27.
 */
public interface CourseSizeDetailDao {
    public void addCourseSizeDetail(CourseSizeDetail courseSizeDetail);

    public void batchAddCourseSizeDetail(List<CourseSizeDetail> list);

    public List<CourseSizeDetail> queryCourseSizeDetail(Integer courseId);
}
