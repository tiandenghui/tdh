package com.kuliao.horcrux.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kuliao.horcrux.app.mapper.UserMapper;
import com.kuliao.horcrux.app.model.User;

@Repository
public class UserDao {
    @Autowired
    UserMapper userMapper;
    
    /**
	 * 用户信息保存
	 * @param user
	 * @return
	 */
    public Integer saveUser(User user){
    	return userMapper.saveUser(user);
    }
    
	/**
	 * 用户信息删除
	 * @param id
	 * @return
	 */
    public int deleteUserById(int id){
    	return userMapper.deleteUserById(id);
    }
    
    /**
	 * 用户信息批量删除
	 * @param params
	 * @return
	 */
    public int deleteUserByIdStr(Map<String, Object> params){
    	return userMapper.deleteUserByIdStr(params);
    }
    
	/**
	 * 用户信息更新
	 * @param user
	 * @return
	 */
    public int updateUser(User user){
    	return userMapper.updateUser(user);
    }
    
	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
    public User getUser(int id){
    	return userMapper.getUser(id);
    }
    
	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
    public User getUserById(int id){
    	return userMapper.getUserById(id);
    }
    
	/**
	 * 根据用户ID查询用户信息，返回Map对象
	 * @param id
	 * @return
	 */
    public Map<String, Object> getUserByIdResultMap(int id){
    	return userMapper.getUserByIdResultMap(id);
    }
    
	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
    public List<Map<String, Object>> queryUserList(Map<String, Object> params){
    	return userMapper.queryUserList(params);
    }
    
	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
    public List<User> queryUserListReturnUser(Map<String, Object> params){
    	return userMapper.queryUserListReturnUser(params);
    }
}
