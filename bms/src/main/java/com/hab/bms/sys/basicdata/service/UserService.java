package com.hab.bms.sys.basicdata.service;

import java.util.Set;

import com.hab.bms.sys.basicdata.model.User;

public interface UserService {

	User findByLoginId(String loginId);

	Set<String> findRoles(String id);

	Set<String> findPermissions(String id);

	int save(User user);
}
