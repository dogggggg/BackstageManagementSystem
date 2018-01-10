package com.hab.bms.sys.shiro.authct;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

import com.hab.bms.sys.shiro.model.UserBaseModel;

public class CustomAuthenticationInfo extends SimpleAuthenticationInfo {

	// TODO WTF?

	/** */
	private static final long serialVersionUID = 4274988245035455812L;

	public CustomAuthenticationInfo(UserBaseModel user, Object principal, Object hashedCredentials,
			ByteSource credentialsSalt, String realmName) {
		super(principal, hashedCredentials, credentialsSalt, realmName);
		this.setUserBaseModel(user);
	}

	/** */
	public CustomAuthenticationInfo() {
	}

	private UserBaseModel userBaseModel;

	/**
	 * Getter method for property <tt>userBaseModel</tt>.
	 * 
	 * @return property value of userBaseModel
	 */
	public UserBaseModel getUserBaseModel() {
		return userBaseModel;
	}

	/**
	 * Setter method for property <tt>userBaseModel</tt>.
	 * 
	 * @param userBaseModel
	 *            value to be assigned to property userBaseModel
	 */
	public void setUserBaseModel(UserBaseModel userBaseModel) {
		this.userBaseModel = userBaseModel;
	}

	@Override
	public void merge(AuthenticationInfo info) {
		super.merge(info);
		if (this.userBaseModel == null && info instanceof CustomAuthenticationInfo) {
			this.userBaseModel = ((CustomAuthenticationInfo) info).getUserBaseModel();
		}
	}
}
