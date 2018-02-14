package com.hab.bms.sys.basicdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hab.bms.sys.basicdata.tools.authc.SSOUsernamePasswordToken;

@Controller
@RequestMapping("/system")
public class DefaultUserLoginContoller extends AbstractLoginContoller {

	@Override
	protected SSOUsernamePasswordToken chooseTokenInstance(String userName, String password, boolean isRememberMe) {
		return new SSOUsernamePasswordToken(userName, password, isRememberMe);
	}
}
