package com.hab.bms.sys.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.tools.IdGen;

public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private final int hashIterations = 2;

	public User encryptPassword(User user) {
		if (StrUtil.isBlank(user.getSalt())) {
			user.setSalt(randomNumberGenerator.nextBytes().toHex());
		}
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
		user.setId(IdGen.uuid());
		return user;
	}

}
