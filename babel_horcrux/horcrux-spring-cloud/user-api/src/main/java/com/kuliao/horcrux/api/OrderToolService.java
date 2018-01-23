package com.kuliao.horcrux.api;

import java.util.List;
import java.util.Map;

public interface OrderToolService {

	String getOrderName(String name);
	
	List<Map<String, Object>> queryUserList(Map<String, Object> params);
}
