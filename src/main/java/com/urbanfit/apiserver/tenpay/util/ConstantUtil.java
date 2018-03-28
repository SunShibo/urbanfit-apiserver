package com.urbanfit.apiserver.tenpay.util;

public class ConstantUtil {
    /**
     * 商家向财付通申请的商家id
     */
    public static final String PARTNER_ID = "1496184812";
    /**
     * 商家可以考虑读取配置文件
     */
    //初始化
    public static String APP_ID = "wxe8909cef1add9d85";      //微信开发平台应用id
    public static String APP_SECRET = "8693752100eae40a160ce4c10a14d167";//应用对应的凭证
    //应用对应的密钥
    //public static String APP_KEY = "9sTB8E2OO7U0fEJwVzgdgcZBVZ0PStPEByMKynhcuPGFSy1gAK8RUnDCYBCjsYkeISLetRsPOHOztIihzmx5h5pVbIb9ryldusmceg21q1wO5YKrghSmliSEygqmBLek";
    public static String PARTNER_KEY = "6895dc07de3749222b9323f706608b1e";//商户号对应的密钥
    public static String TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";//获取access_token对应的url
    public static String GRANT_TYPE = "client_credential";//常量固定值
    public static String ACCESS_TOKEN = "access_token";//access_token常量值
    public static String ERRORCODE = "errcode";//用来判断access_token是否失效的值
    /** 统一下单接口链接 */
    public static final String URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
}
