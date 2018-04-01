package com.urbanfit.apiserver.service;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/1.
 */
public class ActivityMessageServiceTest extends BaseTest{
    @Resource
    private ActivityMessageService activityMessageService;

    @Test
    public void testQueryActivityMessageList(){
        System.out.println(activityMessageService.queryActivityMessageList(null));
    }

    @Test
    public void testQueryActivityMessageDetail(){
        System.out.println(activityMessageService.queryActivityMessageDetail(50));
    }
}
