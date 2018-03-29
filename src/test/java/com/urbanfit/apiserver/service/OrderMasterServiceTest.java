package com.urbanfit.apiserver.service;

import net.sf.json.JSONObject;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/3/29.
 */
public class OrderMasterServiceTest extends BaseTest{
    @Resource
    private OrderMasterService orderMasterService;

    @Test
    public void testAddOrderMaster(){
        // {"childrenName":"zhangsan","clientMobile":"18542632569","courseId":"1","courseDistrict":"beijing","payment":"0"}
        JSONObject jo = new JSONObject();
        jo.put("childrenName", "zhangsan");
        jo.put("clientMobile", "18542632569");
        jo.put("courseId", "1");
        jo.put("courseDistrict", "beijing");
        jo.put("payment", "0");
        jo.put("clientId", 1);
        jo.put("couponNum", "642508317");
        System.out.println(orderMasterService.addClientOrderMaster(jo.toString(), null, null));
    }
}
