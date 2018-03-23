package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.entity.Coupon;
import com.urbanfit.apiserver.service.CouponService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/3/23.
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseCotroller{
    @Resource
    private CouponService couponService;

    @RequestMapping("/list")
    public ModelAndView queryCouponList(Integer status, String couponInfo,
                                        Integer pageNo, Integer pageSize){
        pager = couponService.queryCouponList(status, couponInfo, getQueryInfo(pageNo, pageSize));
        ModelAndView view = new ModelAndView();
        view.setViewName("/coupon/coupon_list");
        view.addObject("lstCoupon", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("status", status);
        view.addObject("couponInfo", couponInfo);
        return view;
    }

    @RequestMapping("/toAdd")
    public ModelAndView redirectToAdd(){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coupon/coupon_add");
        return view;
    }

    @RequestMapping("/add")
    public void addCoupon(HttpServletResponse response, Coupon coupon){
        String result = couponService.addCoupon(coupon);
        safeJsonPrint(response, result);
    }

    @RequestMapping("/detail")
    public ModelAndView queryCouponDetail(HttpServletResponse response, Integer couponId){
        ModelAndView view = new ModelAndView();
        view.setViewName("/coupon/coupon_detail");
        view.addObject("coupon", couponService.queryCouponById(couponId));
        return view;
    }
}
