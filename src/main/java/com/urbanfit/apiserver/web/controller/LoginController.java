package com.urbanfit.apiserver.web.controller;

import com.urbanfit.apiserver.common.constants.SysConstants;
import com.urbanfit.apiserver.entity.UserDO;
import com.urbanfit.apiserver.entity.bo.UserBO;
import com.urbanfit.apiserver.entity.dto.ResultDTOBuilder;
import com.urbanfit.apiserver.entity.dto.param.LoginParam;
import com.urbanfit.apiserver.service.LoginService;
import com.urbanfit.apiserver.util.DateUtils;
import com.urbanfit.apiserver.util.JsonUtils;
import com.urbanfit.apiserver.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shibo Sun
 * 登录controller
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseCotroller {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "loginService")
	LoginService loginService ;

	/**
	 * 登录
	 * @param response
	 * @param loginParam
	 */
    @RequestMapping( value = "/signIn" )
    public void signIn(HttpServletResponse response, LoginParam loginParam){

		/* 1. 验证参数是否完整 */

		if(null == loginParam || StringUtils.isEmpty(loginParam.getPassword()) || StringUtils.isEmpty(loginParam.getAccount())){
			String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常!")) ;
			super.safeJsonPrint(response , json);
			return ;
		}

		/* 2. 找到对应的账户记录 */
		UserBO userBO = loginService.login(loginParam);

		/* 3. 验证账户状态 */
		if (userBO == null ) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010004", "用户名或密码错误!")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (StringUtils.isBlank(userBO.getStatus()) || userBO.getStatus().equals(UserDO.STATUS_FREEZE)) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010005" , "该账户已被冻结!")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (StringUtils.isBlank(userBO.getStatus()) || userBO.getStatus().equals(UserDO.STATUS_INACTIVE)) {
			JSONObject json = new JSONObject() ;
			json.put("userId" , userBO.getId()) ;
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010006" , "该账户没有被激活!", json.toString())) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 4. 登录业务 */
		super.putLoginUser(userBO.getUuid(), userBO); // 保存到缓存
		super.setCookie(response , SysConstants.CURRENT_LOGIN_ID , userBO.getUuid() , SysConstants.SEVEN_DAY_TIME) ;

		/* 5. 返回用户信息 */
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userBO) , DateUtils.DATE_PATTERN) ;
		super.safeJsonPrint(response , result);
	}

	/**
	 * 检查登录状态（长登录）
	 * @param response
	 */
	@RequestMapping( value = "/queryLoginStatus")
	public void queryLoginStatus (HttpServletResponse response, HttpServletRequest request ){

		/* 1. 找到对应的账户记录 */
		UserBO userBO = super.getLoginUser(request) ;

		/* 2. 验证账户状态 */
		if (userBO == null ) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010007" , "用户未登录！")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (StringUtils.isBlank(userBO.getStatus()) || userBO.getStatus().equals(UserDO.STATUS_FREEZE)) {
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010005" , "该账户已被冻结!")) ;
			super.safeJsonPrint(response , result);
			return ;
		}
		if (userBO.getStatus().equals(UserDO.STATUS_INACTIVE) || StringUtils.isBlank(userBO.getStatus())) {
			JSONObject json = new JSONObject() ;
			String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0010006" , "该账户没有被激活!", json.toString())) ;
			json.put("userId" , userBO.getId()) ;
			super.safeJsonPrint(response , result);
			return ;
		}

		/* 3. 返回用户信息 */
		String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(userBO) , DateUtils.DATE_PATTERN) ;
		super.safeJsonPrint(response , result);
	}

}
