package com.urbanfit.apiserver.web.controller.manage;

import com.urbanfit.apiserver.service.OrderMasterService;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/3/25.
 */
@Controller
@RequestMapping("/order")
public class OrderMasterController extends BaseCotroller{
    @Resource
    private OrderMasterService orderMasterService;

    @RequestMapping("/list")
    public ModelAndView queryOrderMasterList(String orderInfo, String provice, String city, String district,
                                             Integer status, Integer pageNo, Integer pageSize){
        pager = orderMasterService.queryOrderMasterList(orderInfo, provice, city, district, status,
                getQueryInfo(pageNo, pageSize));

        ModelAndView view = new ModelAndView();
        view.setViewName("/order/order_master_list");
        view.addObject("lstOrder", pager.getDatas());
        view.addObject("pager", pager);
        view.addObject("pageNo", pageNo);
        view.addObject("orderInfo", orderInfo);
        view.addObject("provice", provice);
        view.addObject("city", city);
        view.addObject("district", district);
        view.addObject("status", status);
        return view;
    }
}
