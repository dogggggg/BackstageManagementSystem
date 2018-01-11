package com.hab.bms.sys.shiro.util;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.hab.bms.sys.utils.DateUtil;

public class MySessionListener1 implements SessionListener {
	@Override
	public void onStart(Session session) {// 会话创建时触发
		System.out.println(DateUtil.formatDateTime(session.getLastAccessTime()) + "，创建会话：" + session.getId());
	}

	@Override
	public void onExpiration(Session session) {// 会话过期时触发
		System.out.println(DateUtil.now() + "，会话过期：" + session.getId());
	}

	@Override
	public void onStop(Session session) {// 退出/会话过期时触发
		System.out.println(DateUtil.formatDateTime(session.getLastAccessTime()) + "，会话停止：" + session.getId());
	}

}
