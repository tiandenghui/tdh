package com.kuliao.horcrux.app.mapper;

import java.util.List;
import java.util.Map;
import com.mysql.jdbc.StringUtils;

public class UserMapperProvider {
	
	/**
	 * 用户信息批量删除动态拼接SQL
	 * @param params
	 * @return
	 */
	public String deleteUserByIdStr(Map<String, Object> params) {      
		StringBuffer sql = new StringBuffer("delete from user where 1=1 and id in ( ");
		List<String> list=(List<String>) params.get("list");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){
				sql.append(list.get(i));
			}else{
				sql.append(list.get(i)+",");
			}
		}
		sql.append(")");
        return sql.toString();
    }
	
	/**
	 * 获取用户信息集合，根据查询条件动态拼接SQL查询语句
	 * @param params
	 * @return
	 */
	public String queryUserListReturnUser(Map<String, Object> params) {
		StringBuffer sql = new StringBuffer("SELECT *,DATE_FORMAT(u.create_date, '%Y-%m-%d %H:%i:%s') create_Date_Str FROM USER u where 1=1 "); 
		String loginId=(String) params.get("loginId");
		String userName=(String) params.get("userName");
		if(!StringUtils.isNullOrEmpty(loginId)){
			sql.append(" AND u.login_id=#{loginId}");
		}
		if(!StringUtils.isNullOrEmpty(userName)){
			sql.append(" AND u.user_name=#{userName}");
		}
        return sql.toString();
    }
}
