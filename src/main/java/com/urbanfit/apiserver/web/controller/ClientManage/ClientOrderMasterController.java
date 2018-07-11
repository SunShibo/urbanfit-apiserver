package com.urbanfit.apiserver.web.controller.ClientManage;

import com.urbanfit.apiserver.entity.OrderMaster;
import com.urbanfit.apiserver.pay.AlipayUtil;
import com.urbanfit.apiserver.service.OrderMasterService;
import com.urbanfit.apiserver.tenpay.handler.PrepayIdRequestHandler;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller ;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/27.
 */
@Controller
@RequestMapping("/corder")
public class ClientOrderMasterController extends BaseCotroller{
    @Resource
    private OrderMasterService orderMasterService;


    @RequestMapping("/list")
    public void queryClientOrderMaster(HttpServletResponse response, Integer clientId, String orderNum,
                                       Integer status, Integer pageNo, Integer pageSize){
        String result = orderMasterService.queryClientOrderMaster(clientId, orderNum, status,
                getQueryInfo(pageNo, pageSize));
        safeTextPrint(response, result);
    }

    @RequestMapping("/detail")
    public void queryClientOrderMasterDetail(HttpServletResponse response, Integer clientId, String orderNum){
        String result = orderMasterService.queryClientOrderMasterDetail(clientId, orderNum);
        safeTextPrint(response, result);
    }

    @RequestMapping("/add")
    public void addClientOrderMaster(HttpServletRequest request, HttpServletResponse response, String params){
        String result = orderMasterService.addClientOrderMaster(params, request, response);
        safeTextPrint(response, result);
    }

    /**
     * @param params {"orderNum" : "", "payment" : ""}
     */
    @RequestMapping("/payAgain")
    public void payOrderMasterAgain(HttpServletRequest request, HttpServletResponse response, String params){
        String result = orderMasterService.payOrderMasterAgain(request, response, params);
        safeTextPrint(response, result);
    }

    @RequestMapping("/orderAlipayCallback")
    public void payOrderMasterSuccessAlipayCallback(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        String result = "fail";
        // 支付宝回调函数参数验证通过
        if(AlipayUtil.checkAlipayCallback(request)){
            Map<String, String> params = AlipayUtil.getAlipayCallbackParams(request);
            if (params.get("trade_status").equals(AlipayUtil.TRADE_SUCCESS)) {
                // 同步支付信息
                orderMasterService.payOrderMasterSuccess(params.get("out_trade_no"), OrderMaster.PAYMENT_ALIPAY);
                result = "success";
            } else if (params.get("trade_status").equals(AlipayUtil.TRADE_FINISHED)) {
                result = "success";
            } else {
                log.debug("支付宝回调支付结果未成功：状态trade_status=" + params.get("trade_status"));
            }
        }else {
            log.debug("---------------支付宝回调签名验证失败-------------");
        }
        safeTextPrint(response, result);
    }

    @RequestMapping("/orderWeChatPayCallback")
    public void payOrderMasterSuccessWeChatPayCallback(HttpServletResponse response) throws Exception{
        System.out.println("调用微信回调函数");
        PrintWriter out = null;
        StringBuffer xmlStr = new StringBuffer();
        try {
            BufferedReader reader = super.getRequest().getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }
            log.debug(">>>>>微信支付通知信息>>>>>>>" + xmlStr.toString());
            Map<String,String> map = PrepayIdRequestHandler.parse2(xmlStr.toString());
            StringBuffer result = new StringBuffer();
            result.append("<xml>");
            if(map != null) {
                log.debug(">>>>>微信支付订单信息>>>>>>>" + map.get("out_trade_no"));
                if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))) {
                    orderMasterService.payOrderMasterSuccess(map.get("out_trade_no"), OrderMaster.PAYMENT_WECHAT);
                    result.append("<return_code>SUCCESS</return_code>");
                    result.append("<return_msg>OK</return_code>");
                }else{
                    result.append("<return_code>FAIL</return_code>");
                    result.append("<return_msg>支付失败</return_code>");
                }
            }else{
                result.append("<return_code>FAIL</return_code>");
                result.append("<return_msg>签名验证失败</return_code>");
            }
            result.append("</xml>");
            safeTextPrint(response, result.toString());
        }catch (Exception e) {
            Logger.getLogger(getClass()).error("qlydweixinotify.do", e);
        } finally {
            if (out != null) {
                out.close();

            }
        }
    }
}
