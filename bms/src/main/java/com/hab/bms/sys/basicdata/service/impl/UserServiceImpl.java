package com.hab.bms.sys.basicdata.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hab.bms.sys.basicdata.model.Resource;
import com.hab.bms.sys.basicdata.model.Role;
import com.hab.bms.sys.basicdata.model.User;
import com.hab.bms.sys.basicdata.mysqlmapper.UserMapper;
import com.hab.bms.sys.basicdata.service.UserService;
import com.hab.bms.sys.utils.PasswordHelper;
import com.hab.bms.sys.utils.StrUtil;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired(required = false)
	private UserMapper userMapper;

	@Override
	public User findByLoginId(String loginId) {
		return userMapper.queryUserByLoginId(loginId);
	}

	@Override
	public Set<String> findRoles(String id) {
		List<Role> roleList = userMapper.queryUserRoleById(id);
		Set<String> roleSet = new HashSet<String>();
		for (Role role : roleList) {
			if (role.getName() != null) {
				roleSet.add(role.getName());
			}
		}
		return roleSet;
	}

	@Override
	public Set<String> findPermissions(String id) {
		List<Role> roleList = userMapper.queryUserRoleById(id);
		Set<String> permissionSet = new HashSet<String>();
		for (Role role : roleList) {
			List<Resource> resourceList = userMapper.queryResourceByRoleId(role.getId());
			for (Resource resource : resourceList) {
				if(resource != null && StrUtil.isNotBlank(resource.getPermission()))
					permissionSet.add(resource.getPermission());
			}
		}
		return permissionSet;
	}

	@Override
	public int save(User user) {
	    User u=new PasswordHelper().encryptPassword(user);  
	    return userMapper.save(u);  
	}
}
