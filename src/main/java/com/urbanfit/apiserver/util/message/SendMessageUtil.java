package com.urbanfit.apiserver.util.message;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/27.
 */
public class SendMessageUtil {
    private final static Logger log = Logger.getLogger(SendMessageUtil.class);

    private static final int APPID = 1400077931;
    private static final String APPKEY = "f0f83079a2c7fd091a039a04bc260741";

    public static String sendMessage(String mobile, String content){
        String sendResult = "fail";
        try {
            SmsSingleSender sender = new SmsSingleSender(APPID, APPKEY);
            SmsSingleSenderResult result = sender.send(0, "86", mobile, "【众力飞特】" + content, "", "98018");
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    public static void main(String args[]) throws Exception{
        /*sendMessage("17610895083", "您的验证为123456");*/

        SmsSingleSender sender = new SmsSingleSender(APPID, APPKEY);
        ArrayList<String> params = new ArrayList<String>();
        params.add("123456");
        SmsSingleSenderResult result = sender.sendWithParam("86", "17610895083", 98018, params, "", "", "");
        System.out.println(result);
    }
}
