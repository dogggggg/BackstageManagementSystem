package com.hab.bms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.service.UserService;

public class TestController extends BaseTest {

	@Autowired
	private UserService userService;

	@Test
	public void userCreate() {
		User user = new User();
		user.setAge(20);
		user.setPassword("1");
		user.setLoginId("1");
		userService.save(user);
	}
}