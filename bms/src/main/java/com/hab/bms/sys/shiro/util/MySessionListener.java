package com.hab.bms.sys.shiro.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import com.hab.bms.sys.utils.DateUtil;

public class MySessionListener implements SessionListener {
	private static final Logger logger = LogManager.getLogger(MySessionListener.class);

	@Override
	public void onStart(Session session) {
		// 会话创建时触发
		logger.debug("创建会话: sessionId={}, creatTime={}", session.getId(),
				DateUtil.formatDateTime(session.getLastAccessTime()));
	}

	@Override
	public void onExpiration(Session session) {
		// 会话过期时触发
		logger.debug("会话过期: sessionId={}, overdueTime={}", session.getId(), DateUtil.now());
	}

	@Override
	public void onStop(Session session) {
		// 退出/会话过期时触发
		logger.debug("会话停止: sessionId={}, creatTime={}", session.getId(),
				DateUtil.formatDateTime(session.getLastAccessTime()));
	}

}
