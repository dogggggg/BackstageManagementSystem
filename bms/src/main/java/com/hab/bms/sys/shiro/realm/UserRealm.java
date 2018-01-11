package com.hab.bms.sys.shiro.realm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.service.UserService;

public class UserRealm extends AuthorizingRealm {
	private static final Logger logger = LogManager.getLogger(UserRealm.class);

	@Autowired
	private UserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String loginId = (String) principals.fromRealm(getName()).iterator().next();
		User user = userService.findByLoginId(loginId);
		logger.info("[用户:" + user.getUserName() + "|权限授权]");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(userService.findRoles(user.getId()));
		authorizationInfo.setStringPermissions(userService.findPermissions(user.getId()));
		logger.info("[用户:" + user.getLoginId() + "|权限授权完成]");
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException { 
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		logger.info("doGetAuthorizationInfo(AuthenticationToken authcToken):" + authenticationToken);
		String loginId = (String) authenticationToken.getPrincipal();
		User user = userService.findByLoginId(loginId);
		if (user == null) {
			throw new UnknownAccountException("用户名未找到");// 没找到帐号
		}
		System.out.println("Realm:" + ByteSource.Util.bytes(user.getCredentialsSalt()));
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), this.getName());// realm
		logger.info("[用户:" + user.getLoginId() + "|系统权限认证完成]");
		return authenticationInfo;
	}

}
