package com.hab.bms;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.service.UserService;

@Controller
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-mybatis.xml")
@RequestMapping("/test")
public class TestController {
	@Resource
	private UserService userService;

	@Test
	public void userCreate() {
		User user = new User();
		user.setAge(20);
		user.setPassword("1");
		user.setUserName("admin");
		user.setLoginId("admin");
		userService.save(user);
	}
}