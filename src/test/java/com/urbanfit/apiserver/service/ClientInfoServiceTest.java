package com.urbanfit.apiserver.service;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/1.
 */
public class ClientInfoServiceTest extends BaseTest{

    @Resource
    private ClientInfoService clientInfoService;
    @Resource
    private ClientMessageService clientMessageService;

    @Test
    public void testClientLogin(){
        System.out.println(clientInfoService.login("13718725223", "7c4a8d09ca3762af61e59520943dc26494f8941b"));
    }

    @Test
    public void testSendCodeForSignIn(){
        System.out.println(clientMessageService.sendCodeForSignIn("17610895083", 1));
    }

    @Test
    public void testUpdatePassword(){
        System.out.println(clientInfoService.updatePassword("13718725223",
                "7c4a8d09ca3762af61e59520943dc26494f8941b", "7c4a8d09ca3762af61e59520943dc26494f8941b"));
    }

}
