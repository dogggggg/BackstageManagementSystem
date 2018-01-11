package com.hab.bms.sys.basicdata.service;

import java.util.Set;

public interface RoleService {

	Set<String> loadRoleIdByLoginId(String loginId);

}
