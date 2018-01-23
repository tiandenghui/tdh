package com.kuliao.horcrux.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuliao.horcrux.app.dao.UserDao;
import com.kuliao.horcrux.app.model.User;


@Service
public class UserService {

    @Autowired
    UserDao userDao;
    
    /**
	 * 用户信息保存
	 * @param user
	 * @return
	 */
    public Integer saveUser(User user){
    	return userDao.saveUser(user);
    }
    
	/**
	 * 用户信息删除
	 * @param id
	 * @return
	 */
    public int deleteUserById(int id){
    	return userDao.deleteUserById(id);
    }
    
    /**
	 * 用户信息批量删除
	 * @param params
	 * @return
	 */
    public int deleteUserByIdStr(Map<String, Object> params){
    	return userDao.deleteUserByIdStr(params);
    }
    
	/**
	 * 用户信息更新
	 * @param user
	 * @return
	 */
    public int updateUser(User user){
    	return userDao.updateUser(user);
    }
    
	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
    public User getUser(int id){
    	return userDao.getUser(id);
    }
    
	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
    public User getUserById(int id){
    	return userDao.getUserById(id);
    }
    
	/**
	 * 根据用户ID查询用户信息，返回Map对象
	 * @param id
	 * @return
	 */
    public Map<String, Object> getUserByIdResultMap(int id){
    	return userDao.getUserByIdResultMap(id);
    }
    
	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
    public List<Map<String, Object>> queryUserList(Map<String, Object> params){
    	return userDao.queryUserList(params);
    }
    
	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
    public List<User> queryUserListReturnUser(Map<String, Object> params){
    	return userDao.queryUserListReturnUser(params);
    }
}
