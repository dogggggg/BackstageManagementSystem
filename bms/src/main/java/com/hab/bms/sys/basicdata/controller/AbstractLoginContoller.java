package com.hab.bms.sys.basicdata.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hab.bms.sys.basicdata.tools.authc.SSOUsernamePasswordToken;
import com.hab.bms.sys.basicdata.tools.commons.Constants;
import com.hab.bms.sys.basicdata.tools.subject.SSOSubject;
import com.hab.bms.sys.basicdata.tools.utils.SSOUtils;
import com.hab.bms.sys.shiro.constants.WebConstants;

public abstract class AbstractLoginContoller {

	protected Logger logger = LogManager.getLogger(getClass());

	private String DEFAULT_SIGNED_LOGINED_URL = "/index.jsp";

	@RequestMapping("/ajaxlogin")
	@ResponseBody
	protected Object ajaxlogin(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		SSOSubject currentUser = SSOUtils.getCurrentUserSubject();
		logger.info("we take u are: name={}, isAuthc={}, isRememberMe={}, isRunAs={}", currentUser.getPrincipal(),
				currentUser.isAuthenticated(), currentUser.isRemembered(), currentUser.isRunAs());
		Map<String, Object> map = new HashMap<String, Object>();
		if (!currentUser.isAuthenticated()) {
			try {
				// 验证码判断
				// checkCode(request);

				currentUser.login(getUsernamePasswrdToken(request));
			} catch (AuthenticationException e) {
				map.put(Constants.KEY_STATUS, Constants.FAILURE);
				if (e instanceof UnknownAccountException) {
					map.put(Constants.KEY_MESSAGE, "用户名不存在");
				} else if (e instanceof IncorrectCredentialsException) {
					map.put(Constants.KEY_MESSAGE, "用户名或密码错误");
				} else if (e instanceof LockedAccountException) {
					map.put(Constants.KEY_MESSAGE, "用户已锁定，请联系客服");
				} else {
					logger.error("登陆发生异常", e);
					map.put(Constants.KEY_MESSAGE, "系统繁忙，请稍后再试");
				}
				return map;
			}
		}
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		return map;
	}

	private SSOUsernamePasswordToken getUsernamePasswrdToken(HttpServletRequest request) {
		String username = request.getParameter(WebConstants.LOGIN_ID);
		String password = request.getParameter(WebConstants.PASSWORD);
		String rememberMe = request.getParameter(WebConstants.REMEMBER_ME);
		logger.info("get userInfo, username={}, password={}, rememberMe={}", username, password, rememberMe);
		return chooseTokenInstance(username, password,
				org.apache.shiro.web.util.WebUtils.isTrue(request, WebConstants.REMEMBER_ME));
	}

	/**
	 * 生成对应的Token
	 * 
	 * @param userName
	 * @param password
	 * @param isRememberMe
	 * @return
	 */
	protected abstract SSOUsernamePasswordToken chooseTokenInstance(String userName, String password,
			boolean isRememberMe);

	protected String getDefaultGotoUrl() {
		return DEFAULT_SIGNED_LOGINED_URL;
	}

}