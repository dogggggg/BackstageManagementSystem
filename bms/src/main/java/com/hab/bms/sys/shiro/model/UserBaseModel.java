package com.hab.bms.sys.shiro.model;

import java.io.Serializable;

public class UserBaseModel extends AbstractUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1148279759072125995L;
	
	/** 用户名 **/
	private String userName;
	/** 部门Id **/
	private String orgId;
	/** 登录类型 **/
	private int loginType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

}
