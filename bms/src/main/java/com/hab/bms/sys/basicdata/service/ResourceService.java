package com.hab.bms.sys.basicdata.service;

import java.util.Set;

public interface ResourceService {

	Set<String> loadPermissionsByLoginId(String loginId);

}
