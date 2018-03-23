package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.CouponDao;
import com.urbanfit.apiserver.entity.Coupon;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.util.RandomUtils;
import com.urbanfit.apiserver.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/23.
 */
@Service("couponService")
@Transactional
public class CouponService {
    @Resource
    private CouponDao couponDao;

    public PageObject<Coupon> queryCouponList(Integer status, String couponInfo, QueryInfo queryInfo){
        Map<String, Object> map = new HashMap<String, Object>();
        if(queryInfo != null){
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        if(!StringUtils.isEmpty(couponInfo)){
            map.put("couponInfo", couponInfo);
        }
        PageObjectUtil<Coupon> page = new PageObjectUtil<Coupon>();
        return page.savePageObject(couponDao.queryCouponCount(map), couponDao.queryCouponList(map), queryInfo);
    }

    public String addCoupon(Coupon coupon){
        if(coupon == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        if(StringUtils.isEmpty(coupon.getCouponName()) || StringUtils.isEmpty(coupon.getSourceName())
                || coupon.getPercent() == null || coupon.getBeginTime() == null || coupon.getEndTime() == null){

            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        // 生成优惠码
        coupon.setCouponNum(getCouponNum());
        couponDao.addCoupon(coupon);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加优惠码成功", "").toString();
    }

    public Coupon queryCouponById(Integer couponId){
        return couponDao.queryCouponById(couponId);
    }

    private String getCouponNum() {
        String couponNum = "";
        do {
            couponNum = RandomUtils.getRandomNumber(11);
            //查询邀请码是否存在
            if (couponDao.queryCouponCountByNum(couponNum) == 0)
                break;
        } while (true);
        return couponNum;
    }
}
