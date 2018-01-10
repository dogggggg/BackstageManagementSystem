package com.hab.bms.sys.shiro.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hab.bms.sys.shiro.authc.service.UserAuthcService;

public class SysUserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource(name = "sysUserAuthcService")
	private UserAuthcService userAuthcService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token == null || token instanceof SysUserToken;
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		return principals != null ? principals.toString() : "";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

}
