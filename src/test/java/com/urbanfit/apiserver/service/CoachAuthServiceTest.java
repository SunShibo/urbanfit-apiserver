package com.urbanfit.apiserver.service;

import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018/4/1.
 */
public class CoachAuthServiceTest extends BaseTest{
    @Resource
    private CoachAuthService coachAuthService;

    @Test
    public void testQueryCoachAuth(){
        System.out.println(coachAuthService.queryCoachAuth("", ""));
    }
}
