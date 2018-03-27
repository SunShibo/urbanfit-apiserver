package com.urbanfit.apiserver.util.message;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.apache.log4j.Logger;

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
            SmsSingleSenderResult result = sender.send(0, "86", mobile, "【众力飞特】" + content, "", "123");
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }

    public static void main(String args[]){
        sendMessage("17610895083", "您的验证为123456");
    }
}
