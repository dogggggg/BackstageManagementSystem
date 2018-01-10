package com.hab.bms.sys.shiro.authc.mysqlmapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hab.bms.sys.shiro.model.UserBaseModel;

public interface SysUserAuthcMapper {

	@Select("SELECT SL.* FROM SYS_USER SL WHERE SL.LOGIN_ID=#{loginId,jdbcType=VARCHAR}")
	UserBaseModel queryUserByLoginId(@Param("loginId") String loginId);

	@Insert("INSERT INTO SYS_USER(LOGIN_ID, USER_NAME, PASSWORD,SALT, LOCKED, LOGIN_TYPE) VALUES("
			+ "#{loginId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=NUMERIC},#{loginType, jdbcType=NUMERIC})")
	void saveUserLogin(UserBaseModel userBaseModel);

}
