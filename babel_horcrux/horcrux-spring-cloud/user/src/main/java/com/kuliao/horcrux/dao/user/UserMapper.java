package com.kuliao.horcrux.dao.user;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryUserList(Map<String, Object> params);
}
