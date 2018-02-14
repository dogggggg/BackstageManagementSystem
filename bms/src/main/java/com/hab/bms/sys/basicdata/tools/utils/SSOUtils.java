package com.hab.bms.sys.basicdata.tools.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.tools.subject.SSOSubject;
import com.hab.bms.sys.shiro.constants.WebConstants;

public class SSOUtils {

	public static SSOSubject getCurrentUserSubject() {
		return new SSOSubject(SecurityUtils.getSubject());
	}

	public static User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(false);
		return (User) session.getAttribute(WebConstants.CURRENT_USER);
	}

	/**
	 * 获取当前登录用户ID
	 * 
	 * @return
	 */
	public static String getCurrentUserId() {
		return getCurrentUser().getId();
	}

}
