package com.urbanfit.apiserver.timer;

import com.urbanfit.apiserver.service.CouponService;
import com.urbanfit.apiserver.service.OrderMasterService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/3.
 */
@Component
public class Timer {
    private Logger log = Logger.getLogger(Timer.class);

    @Resource
    private OrderMasterService orderMasterService;
    @Resource
    private CouponService couponService;

    /**
     * 系统自动取消订单
     */
    @Scheduled(cron = "0 0/5 * * * *")
    public void systemCancleOrderMaster(){
        log.info("===================取消订单定时器开始=========================");
        orderMasterService.systemCancleOrderMaster();
        log.info("===================取消订单定时器结束=========================");
    }

    /**
     * 优惠码设置为已过期
     */
    @Scheduled(cron = "0 0/5 * * * *")
    public void systemSetCouponExpired(){
        log.info("===================优惠码过期定时器开始=========================");
        couponService.updateCouponExpired();
        log.info("===================优惠码过期定时器结束=========================");
    }
}
