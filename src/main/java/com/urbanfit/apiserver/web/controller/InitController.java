package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shibo Sun
 * 主机controller
 */
@Controller
@RequestMapping("/init")
public class InitController extends BaseCotroller {

	static final Logger log = LoggerFactory.getLogger(InitController.class);


	/**
	 * 查询所有容器
	 *
	 * @param response
	 */
	@RequestMapping("/home")
	public ModelAndView home(HttpServletResponse response) {

		Map m = Collections.synchronizedMap(new HashMap());
		ModelAndView modelAndView = new ModelAndView("home/home");
		return modelAndView;
	}

}