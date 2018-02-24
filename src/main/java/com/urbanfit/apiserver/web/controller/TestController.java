package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.entity.HostDO;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    public void addHost(HttpServletResponse response, HostDO hostDO) {
//        hostDAO.insertHost(hostDO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("request success"));
        super.safeJsonPrint(response, json);
    }

}
