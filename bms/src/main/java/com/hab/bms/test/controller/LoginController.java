package com.hab.bms.test.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hab.bms.sys.basicdata.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
	@Resource
	private UserService userService;

	/**
	 * @Title: loginView @Description: TODO(转向登录界面) @param @return 设定文件 @return
	 * String 返回类型 @throws
	 */
	@RequestMapping(value = "/login")
	public String loginView() {
		return "login";
	}

	/**
	 * @Title: login1 @Description: TODO(shiro+EndecryptUtils进行认证) @param @param
	 * request @param @param model @param @param username @param @param
	 * password @param @return 设定文件 @return String 返回类型 @throws
	 */
	@RequestMapping(value = "/loginTest")
	public String login(HttpServletRequest request, Model model, String username, String password, boolean rememberMe) {
		System.out.println("rememberMe:" + rememberMe);
		// 将form中的用户名密码传入Realm 的doGetAuthenticationInfo
		UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
		token.setRememberMe(rememberMe);
		Subject currentUser = SecurityUtils.getSubject();
		String error = "";
		try {
			currentUser.login(token);
		} catch (UnknownAccountException ex) {// 用户名没有找到
			error = "您输入的用户名不存在！";
		} catch (IncorrectCredentialsException ex) {// 用户名密码不匹配
			error = "用户名密码不匹配 ！";
		} catch (ExcessiveAttemptsException e) {
			error = "密码错误次数已超五次，账号锁定1小时！";
		} catch (AuthenticationException ex) {// 其他的登录错误
			ex.printStackTrace();
			error = "其他的登录错误  ！";
		}
		// 验证是否成功登录的方法
		if (currentUser.isAuthenticated()) {
			return "redirect:/admin/index";
		} else {
			model.addAttribute("message", error);
			currentUser.logout();
			return "login";
		}

	}

}