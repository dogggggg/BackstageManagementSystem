package com.hab.bms.sys.basicdata.mysqlmapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hab.bms.sys.basicdata.model.Resource;
import com.hab.bms.sys.basicdata.model.Role;
import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.tools.mysqlmapper.BaseDao;

public interface UserMapper extends BaseDao<User> {

	@Select("SELECT SL.* FROM SYS_USER SL WHERE SL.LOGIN_ID=#{loginId,jdbcType=VARCHAR}")
	User queryUserByLoginId(@Param("loginId") String loginId);

	@Select("SELECT SR.ID ID, SR.ROLE_NAME DESCRIPTION, SR.ROLE_CODE ROLE FROM SYS_USER_ROLE SUR LEFT JOIN SYS_ROLE SR ON SUR.ROLE_ID = SR.ID WHERE SUR.USER_ID=#{id,jdbcType=VARCHAR}")
	List<Role> queryUserRoleById(@Param("id") String id);

	@Select("SELECT SR.ID ID, SR.ROLE_NAME description, SR.ROLE_CODE ROLE FROM SYS_ROLE SR ")
	List<Role> queryAllRole();

	@Select(" SELECT SM.ID ID,SM.MENU_NAME NAME ,SM.URL,SM.PERMISSION,SM.PARENT_ID,SM.TYPE FROM SYS_MENU SM WHERE EXISTS( SELECT ROLE_ID FROM SYS_MENU_ROLE SMR WHERE SMR.MENU_ID = SM.ID AND SMR.ROLE_ID = #{roleId,jdbcType=VARCHAR})")
	List<Resource> queryResourceByRoleId(@Param("roleId") String roleId);

//	@Insert("INSERT INTO SYS_USER(LOGIN_ID, USER_NAME, PASSWORD,SALT, LOCKED, LOGIN_TYPE) VALUES("
//			+ "#{loginId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=NUMERIC},#{loginType, jdbcType=NUMERIC})")
//	void saveUserLogin(User user);

	@Insert("INSERT INTO SYS_USER(LOGIN_ID, USER_NAME, PASSWORD,SALT, LOCKED, LOGIN_TYPE) VALUES("
			+ "#{loginId,jdbcType=VARCHAR},#{userName,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR},#{salt,jdbcType=VARCHAR},#{locked,jdbcType=NUMERIC},#{loginType, jdbcType=NUMERIC})")
	int save(User user);

}