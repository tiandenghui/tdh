package com.kuliao.horcrux.biz.service.api.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.kuliao.horcrux.api.OrderToolService;
import com.kuliao.horcrux.dao.user.UserMapper;

public class OrderToolServiceImpl implements OrderToolService {

	@Autowired
	UserMapper userMapper;
	
	public String getOrderName(String name) {
        return "orderName=="+name;
	}

	public List<Map<String, Object>> queryUserList(Map<String, Object> params) {
		return userMapper.queryUserList(params);
	}
}
