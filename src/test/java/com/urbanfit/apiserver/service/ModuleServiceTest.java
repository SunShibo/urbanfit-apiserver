package com.urbanfit.apiserver.service;

import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * Created by wangyubo on 2018/4/5.
 */
public class ModuleServiceTest extends BaseTest{
    @Resource
    private ModuleService moduleService;

    @Test
    public void testQueryModule(){
        System.out.println(moduleService.queryModuleList(1));
    }
}
