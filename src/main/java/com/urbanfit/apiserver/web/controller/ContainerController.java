//package com.urbanfit.apiserver.web.controller;
//
//import com.github.dockerjava.api.exception.InternalServerErrorException;
//import com.github.dockerjava.api.exception.NotModifiedException;
//import com.github.dockerjava.api.model.Container;
//import com.urbanfit.apiserver.core.ContainerEngine;
//import com.urbanfit.apiserver.core.HostEngine;
//import com.urbanfit.apiserver.dao.HostDAO;
//import com.urbanfit.apiserver.entity.HostDO;
//import com.urbanfit.apiserver.entity.bo.HostBO;
//import com.urbanfit.apiserver.entity.dto.ResultDTO;
//import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
//import com.urbanfit.apiserver.util.JsonUtils;
//import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
///**
// * @author Shibo Sun
// *         主机controller
// */
//@Controller
//@RequestMapping("/container")
//public class ContainerController extends BaseCotroller {
//
//    static final Logger log = LoggerFactory.getLogger(ContainerController.class);
//
//    @Resource(name = "containerEngine")
//    private ContainerEngine containerEngine;
//
//
//    /**
//     * 查询所有容器
//     *
//     * @param response
//     */
//    @RequestMapping("/list")
//    public ModelAndView containerList(HttpServletResponse response) {
//        List<Container> allContainer = containerEngine.getAllContainer();
//        ResultDTO<List<Container>> data = ResultDTOBuilder.success(allContainer);
//
//        ModelAndView maw = new ModelAndView("container/containerList");
//        maw.addObject("data" , data);
//        return maw;
//    }
//
//    /**
//     * 回复暂停的container
//     *
//     * @param response
//     */
//    @RequestMapping("/unpauseContainer")
//    public void unpauseContainer(HttpServletResponse response, int hostID, String containerID) {
//        boolean result = false;
//        try {
//            result = containerEngine.unpauseContainer(hostID, containerID);
//            String json;
//            if (result) {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
//            } else {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure(""));
//            }
//            super.safeJsonPrint(response, json);
//        } catch (InternalServerErrorException e) {
//            e.printStackTrace();
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(e.getMessage()));
//            super.safeJsonPrint(response, json);
//        }
//
//    }
//
//    /**
//     * 暂停container
//     *
//     * @param response
//     */
//    @RequestMapping("/pauseContainer")
//    public void pauseContainer(HttpServletResponse response, int hostID, String containerID) {
//        boolean result = false;
//        try {
//            result = containerEngine.pauseContainer(hostID, containerID);
//            String json;
//            if (result) {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
//            } else {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure(""));
//            }
//            super.safeJsonPrint(response, json);
//        } catch (InternalServerErrorException e) {
//            e.printStackTrace();
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(e.getMessage()));
//            super.safeJsonPrint(response, json);
//        }
//    }
//
//    /**
//     * 停止container
//     *
//     * @param response
//     */
//    @RequestMapping("/stopContainer")
//    public void stopContainer(HttpServletResponse response, int hostID, String containerID) {
//        try {
//            boolean result = containerEngine.stopContainer(hostID, containerID);
//            String json;
//            if (result) {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
//            } else {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure(""));
//            }
//            super.safeJsonPrint(response, json);
//        } catch (NotModifiedException e) {
//            e.printStackTrace();
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(e.toString()));
//            super.safeJsonPrint(response, json);
//        }
//    }
//
//    /**
//     * 启动container
//     *
//     * @param response
//     */
//    @RequestMapping("/startContainer")
//    public void startContainer(HttpServletResponse response, int hostID, String containerID) {
//        try {
//            boolean result = containerEngine.startContainer(hostID, containerID);
//            String json;
//            if (result) {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(result));
//            } else {
//                json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure(""));
//            }
//            super.safeJsonPrint(response, json);
//        } catch (Exception e) {
//            e.printStackTrace();
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(e.toString()));
//            super.safeJsonPrint(response, json);
//        }
//    }
//
//
//}
