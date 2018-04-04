package com.urbanfit.apiserver.service;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.dao.CouponDao;
import com.urbanfit.apiserver.entity.Coupon;
import com.urbanfit.apiserver.query.PageObject;
import com.urbanfit.apiserver.query.PageObjectUtil;
import com.urbanfit.apiserver.query.QueryInfo;
import com.urbanfit.apiserver.util.DateUtils;
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
        if(status != null){
            map.put("status", status);
        }
        PageObjectUtil<Coupon> page = new PageObjectUtil<Coupon>();
        return page.savePageObject(couponDao.queryCouponCount(map), couponDao.queryCouponList(map), queryInfo);
    }

    public String addCoupon(String couponName, String sourceName, Double percent, String beginTime, String endTime){
        if(StringUtils.isEmpty(couponName) || StringUtils.isEmpty(sourceName) || percent == null
                || StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponName);
        coupon.setSourceName(sourceName);
        coupon.setPercent(percent);
        coupon.setBeginTime(DateUtils.parseDate(beginTime, DateUtils.DATE_PATTERN));
        coupon.setEndTime(DateUtils.parseDate(endTime, DateUtils.DATE_PATTERN));
        // 生成优惠码
        coupon.setCouponNum(getCouponNum());
        couponDao.addCoupon(coupon);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "添加优惠码成功", "").toString();
    }

    public Coupon queryCouponById(Integer couponId){
        return couponDao.queryCouponById(couponId);
    }

    public String updateCouponStatus(Integer couponId){
        if(couponId == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_PARAM_ERROR, "参数有误", "").toString();
        }
        Coupon coupon = couponDao.queryCouponById(couponId);
        if(coupon == null){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "数据不存在", "").toString();
        }
        if(coupon.getStatus() == Coupon.STATUS_EXPIRED){
            return JsonUtils.encapsulationJSON(Constant.INTERFACE_FAIL, "优惠码以为过期状态", "").toString();
        }
        // 修改状态
        couponDao.updateCouponStatus(couponId);
        return JsonUtils.encapsulationJSON(Constant.INTERFACE_SUCC, "设置为过期状态成功", "").toString();
    }

    public void updateCouponExpired(){
        couponDao.updateCouponExpired();
    }

    private String getCouponNum() {
        String couponNum = "";
        do {
            couponNum = RandomUtils.getRandomNumber(9);
            //查询邀请码是否存在
            if (couponDao.queryCouponCountByNum(couponNum) == 0)
                break;
        } while (true);
        return couponNum;
    }
}
