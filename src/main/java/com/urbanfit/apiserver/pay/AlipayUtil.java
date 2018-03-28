package com.urbanfit.apiserver.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.urbanfit.apiserver.cfg.pop.SystemConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/28.
 */
public class AlipayUtil {
    //交易创建
    public final static String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
    //支付成功
    public final static String TRADE_SUCCESS = "TRADE_SUCCESS";
    //交易成功
    public final static String TRADE_FINISHED = "TRADE_FINISHED";

    public static final String APP_ID = SystemConfig.getString("alipay_appid");
    public static final String APP_PRIVATE_KEY = SystemConfig.getString("alipay_app_private_key");
    public static final String CHARSET = SystemConfig.getString("alipay_charset");
    public static final String ALIPAY_PUBLIC_KEY = SystemConfig.getString("alipay_public_key");

    public String getAlipayInfo(){
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("众力飞特");
        model.setSubject("众力飞特支付");
        model.setOutTradeNo(System.currentTimeMillis() + "");
        model.setTimeoutExpress("30m");
        model.setTotalAmount("0.01");
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://121.42.29.186:8091/facade/alipay_alipayCallback.do");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return  response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String submitClientlipay(String body, String subject, String orderNum, Double price,
                                    String callbackUrl){
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(body);
        model.setSubject(subject);
        model.setOutTradeNo(orderNum);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(price.toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(callbackUrl);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            System.out.println(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return  response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean checkAlipayCallback(HttpServletRequest request) throws Exception{
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            params.put(name, valueStr);
        }
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        return AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2");
    }

    public static Map<String, String> getAlipayCallbackParams(HttpServletRequest request){
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }
}
