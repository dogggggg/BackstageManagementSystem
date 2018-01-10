package com.hab.bms.sys.shiro.model;

import java.io.Serializable;

public class AbstractUser implements Serializable {

	/** */
	private static final long serialVersionUID = 4199106257039093971L;

	private String id;
	private String loginId;
	private String password;
	/** 加密密码的盐 **/
	private String salt;
	/** 是否锁定 0-未锁定 -1-锁定 */
	private int locked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	@Override
	public int hashCode() {
		return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractUser other = (AbstractUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractUser [id=" + id + ", loginId=" + loginId + ", password=" + password + ", salt=" + salt
				+ ", locked=" + locked + "]";
	}

	/**
	 * 上锁
	 * 
	 * @return
	 */
	public boolean isUserLocked() {
		return this.locked == -1;
	}

	/**
	 * 获取加盐证书
	 * 
	 * @return
	 */
	public String getCredentialsSalt() {
		return loginId + salt;
	}

}
