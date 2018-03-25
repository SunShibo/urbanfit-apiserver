package com.urbanfit.apiserver.dao;

import com.urbanfit.apiserver.entity.Coupon;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/23.
 */
public interface CouponDao{
    public void addCoupon(Coupon coupon);

    public Coupon queryCouponByNum(String couponNum);

    public int queryCouponCountByNum(String couponNum);

    public int queryCouponCount(Map<String, Object> map);

    public List<Coupon> queryCouponList(Map<String, Object> map);

    public Coupon queryCouponById(Integer couponId);

    public void updateCouponStatus(Integer couponId);
}
