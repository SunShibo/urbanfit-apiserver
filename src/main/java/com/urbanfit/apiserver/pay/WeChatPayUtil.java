package com.urbanfit.apiserver.pay;

import com.urbanfit.apiserver.cfg.pop.Constant;
import com.urbanfit.apiserver.tenpay.handler.AccessTokenRequestHandler;
import com.urbanfit.apiserver.tenpay.handler.ClientRequestHandler;
import com.urbanfit.apiserver.tenpay.handler.PackageRequestHandler;
import com.urbanfit.apiserver.tenpay.handler.PrepayIdRequestHandler;
import com.urbanfit.apiserver.tenpay.util.ConstantUtil;
import com.urbanfit.apiserver.tenpay.util.WXUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Administrator on 2018/3/28.
 */
public class WeChatPayUtil {
    private static final Logger logger = Logger.getLogger(WeChatPayUtil.class);

    public static JSONObject submitPrepayToWeChat(HttpServletRequest request, HttpServletResponse response,
                                           String orderNum, String goodsName, int total, String body,
                                           String trade_type, String callbackUrl) {
        PackageRequestHandler packageReqHandler = new PackageRequestHandler(
                request, response);// 生成package的请求类
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(
                request, response);// 获取prepayid的请求类
        ClientRequestHandler clientHandler = new ClientRequestHandler(request,
                response);// 返回客户端支付参数的请求类
        packageReqHandler.setKey(ConstantUtil.PARTNER_KEY);

        JSONObject json = new JSONObject();
        // 获取token值
        String token = AccessTokenRequestHandler.getAccessToken();
        if (!"".equals(token)) {
            try{
                // 装载postData参数
                createOrderPostData(request, orderNum, goodsName, total, packageReqHandler, callbackUrl);
                String noncestr = UUID.randomUUID().toString().replace("-", "");
                String timestamp = WXUtil.getTimeStamp();
                // //设置获取prepayid支付参数
                prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);
                prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER_ID);
                prepayReqHandler.setParameter("nonce_str", noncestr);
                prepayReqHandler.setParameter("body", body);
                prepayReqHandler.setParameter("notify_url", callbackUrl);
                prepayReqHandler.setParameter("out_trade_no", orderNum);
                prepayReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
                prepayReqHandler.setParameter("total_fee", total+"");
                prepayReqHandler.setParameter("trade_type", trade_type);

                // 生成获取预支付签名
                String sign = prepayReqHandler.createMD5Sign(ConstantUtil.PARTNER_KEY);
                prepayReqHandler.setParameter("sign", sign);
                // 获取prepayId
                String prepayid = prepayReqHandler.sendPrepay();
                // 吐回给客户端的参数
                if (null != prepayid && !"".equals(prepayid)) {
                    // 输出参数列表
                    clientHandler.setParameter("appid", ConstantUtil.APP_ID);
                    clientHandler.setParameter("noncestr", noncestr);
                    clientHandler.setParameter("package", "Sign=WXPay");
                    clientHandler
                            .setParameter("partnerid", ConstantUtil.PARTNER_ID);
                    clientHandler.setParameter("prepayid", prepayid);
                    clientHandler.setParameter("timestamp", timestamp);
                    // 生成签名
                    sign = clientHandler.createMD5Sign2(ConstantUtil.PARTNER_KEY);;
                    clientHandler.setParameter("sign", sign);
                    JSONObject jo = new JSONObject();
                    jo.put("timeStamp", timestamp);
                    jo.put("nonceStr", noncestr);
                    jo.put("orderNum", orderNum);
                    jo.put("prepayid", prepayid);
                    jo.put("package", "Sign=WXPay");
                    jo.put("timeStamp", timestamp);
                    jo.put("nonceStr", noncestr);
                    jo.put("appId", ConstantUtil.APP_ID);
                    jo.put("partnerId", ConstantUtil.PARTNER_ID);
                    jo.put("appSecret", ConstantUtil.APP_SECRET);
                    jo.put("sign", sign);
                    json.put("code", Constant.INTERFACE_SUCC);
                    json.put("msg", "调用微信支付成功");
                    json.put("data", jo.toString());
                    System.out.println("微信支付 订单提交成功 ！prepayid=" + prepayid);
                } else {
                    json.put("code", Constant.INTERFACE_FAIL);
                    json.put("msg", "错误：获取prepayId失败");
                    json.put("data", "");
                }
            }catch(Exception e){
                json.put("code", Constant.INTERFACE_FAIL);
                json.put("msg", "错误");
                json.put("data", "");
            }
        } else {
            json.put("code", Constant.INTERFACE_FAIL);
            json.put("msg", "错误：获取不到Token");
            json.put("data", "");
        }
        return json;
    }

    public static void createOrderPostData(HttpServletRequest request, String orderNum, String goodsName, int total,
                                    PackageRequestHandler packageReqHandler, String callbackUrl) {

        logger.info(">>>>>>>>>>>>>> callbackUrl = " + callbackUrl);

        // 设置package订单参数
        packageReqHandler.setParameter("bank_type", "WX");// 银行渠道
        packageReqHandler.setParameter("body", goodsName); // 商品描述

        //判断服务器环境
        packageReqHandler.setParameter("notify_url", callbackUrl);
        packageReqHandler.setParameter("partner", ConstantUtil.PARTNER_ID); // 商户号
        packageReqHandler.setParameter("out_trade_no", orderNum); // 商家订单号
        packageReqHandler.setParameter("total_fee", total + ""); // 商品金额,以分为单位
        // 订单生成的机器IP，指用户浏览器端IP
        packageReqHandler.setParameter("spbill_create_ip", request.getRemoteAddr());
        packageReqHandler.setParameter("fee_type", "1"); // 币种，1人民币 66
        packageReqHandler.setParameter("input_charset", "GBK"); // 字符编码
    }
}
