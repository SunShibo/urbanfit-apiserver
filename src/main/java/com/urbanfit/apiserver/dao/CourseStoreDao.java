package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.CourseStore;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30.
 */
public interface CourseStoreDao {

    public void batchAddCourseStore(List<CourseStore> list);

    public void deleteCourseStore(Integer courseId);

    public void deleteCourseStoreByStoreId(Integer storeId);

    public List<CourseStore> queryStoreChoosedCourse(Integer storeId);
}
