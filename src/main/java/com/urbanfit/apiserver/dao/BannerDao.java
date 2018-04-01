package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Banner;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/1.
 */
public interface BannerDao {

    public int queryBannerCount(Map<String, Object> map);

    public List<Banner> queryBannerList(Map<String, Object> map);

    public Banner queryBannerById(Integer bannerId);

    public void deleteBanner(Integer bannerId);

    public void addBanner(Banner banner);

    public void updateBanner(Banner banner);
}
