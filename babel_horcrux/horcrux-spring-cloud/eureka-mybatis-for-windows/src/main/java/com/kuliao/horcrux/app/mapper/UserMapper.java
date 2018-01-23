package com.kuliao.horcrux.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.kuliao.horcrux.app.model.User;

public interface UserMapper {

	/**
	 * 用户信息保存
	 * @param user
	 * @return
	 */
	@Insert("insert into user(login_id,login_pwd) values(#{loginId},#{loginPwd})")
	@Options(useGeneratedKeys = true, keyProperty = "id")	
	int saveUser(User user);

	/**
	 * 用户信息删除
	 * @param id
	 * @return
	 */
	@Delete("delete from user where id=#{id}")
	int deleteUserById(int id);

	/**
	 * 用户信息批量删除
	 * @param params
	 * @return
	 */
	@DeleteProvider(type = UserMapperProvider.class, method = "deleteUserByIdStr")
	int deleteUserByIdStr(Map<String, Object> params);

	/**
	 * 用户信息更新
	 * @param user
	 * @return
	 */
	@Update("update user set login_id=#{loginId},login_pwd=#{loginPwd},user_name=#{userName} where id=#{id}")
	int updateUser(User user);

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
	@Select("select *,DATE_FORMAT(u.create_date, '%Y-%m-%d %H:%i:%s') create_Date_Str from user u where id=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"),
			@Result(column = "login_id", property = "loginId"),
			@Result(column = "login_pwd", property = "loginPwd"),
			@Result(column = "user_name", property = "userName"),
			@Result(column = "create_date", property = "createDate"),
			@Result(column = "update_date", property = "updateDate"),
			@Result(column = "create_Date_Str", property = "createDateStr") })
	User getUser(int id);

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 */
	@Select("select *,DATE_FORMAT(u.create_date, '%Y-%m-%d %H:%i:%s') create_Date_Str from user u where id=#{id}")
	@Results({ @Result(id = true, column = "id", property = "id"),
			@Result(column = "login_id", property = "loginId"),
			@Result(column = "login_pwd", property = "loginPwd"),
			@Result(column = "user_name", property = "userName"),
			@Result(column = "create_date", property = "createDate"),
			@Result(column = "update_date", property = "updateDate"),
			@Result(column = "create_Date_Str", property = "createDateStr") })
	User getUserById(int id);

	/**
	 * 根据用户ID查询用户信息，返回Map对象
	 * @param id
	 * @return
	 */
	@Select("SELECT u.id,u.login_id loginId,u.login_pwd,u.user_name userName,DATE_FORMAT(u.create_date, '%Y-%m-%d %H:%i:%s') create_Date_Str FROM USER u WHERE u.id=#{id}")
	Map<String, Object> getUserByIdResultMap(int id);

	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
	@Select("SELECT u.id,u.login_id loginId,u.login_pwd,u.user_name userName,DATE_FORMAT(u.create_date, '%Y-%m-%d %H:%i:%s') create_Date_Str FROM USER u")
	List<Map<String, Object>> queryUserList(Map<String, Object> params);

	/**
	 * 获取用户信息集合
	 * @param params
	 * @return
	 */
	@SelectProvider(type = UserMapperProvider.class, method = "queryUserListReturnUser")
	@Results({ @Result(id = true, column = "id", property = "id"),
			@Result(column = "login_id", property = "loginId"),
			@Result(column = "login_pwd", property = "loginPwd"),
			@Result(column = "user_name", property = "userName"),
			@Result(column = "create_date", property = "createDate"),
			@Result(column = "update_date", property = "updateDate"),
			@Result(column = "create_Date_Str", property = "createDateStr") })
	List<User> queryUserListReturnUser(Map<String, Object> params);
}
