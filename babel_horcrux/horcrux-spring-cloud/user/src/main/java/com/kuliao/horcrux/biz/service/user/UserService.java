package com.kuliao.horcrux.biz.service.user;

import java.util.List;
import java.util.Map;

public interface UserService {

	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryUserList(Map<String, Object> params);
}
