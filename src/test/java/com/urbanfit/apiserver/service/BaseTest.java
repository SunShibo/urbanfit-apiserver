package com.urbanfit.apiserver.service;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2018/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/conf/spring/application-context.xml", "/conf/spring/mvc-config.xml"})

public class BaseTest {

    long start = 0L;

    @org.junit.Before
    public void start() {
        start = System.currentTimeMillis();
    }

    @After
    public void end() {
        long end = System.currentTimeMillis();
        System.out.println("--------------> 执行时间: " + (end - start) + "ms");
    }

}