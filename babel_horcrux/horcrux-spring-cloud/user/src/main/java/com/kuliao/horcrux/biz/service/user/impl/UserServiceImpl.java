package com.kuliao.horcrux.biz.service.user.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliao.horcrux.biz.service.user.UserService;
import com.kuliao.horcrux.dao.user.UserMapper;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> queryUserList(Map<String, Object> params) {
		return userMapper.queryUserList(params);
	}

}
