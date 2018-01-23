package com.kuliao.horcrux.app.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kuliao.horcrux.api.OrderToolService;
import com.kuliao.horcrux.api.TestToolService;
import com.kuliao.horcrux.app.model.User;
import com.kuliao.horcrux.app.service.UserService;
import com.kuliao.horcrux.app.utils.JsonUtil;
import com.kuliao.horcrux.app.utils.MsgCode;
import com.kuliao.horcrux.common.ServiceLocator;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * 用户信息保存
	 * @param loginId 登陆账号
	 * @param loginPwd 登陆密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveUser")
	public @ResponseBody Map<String, Object> saveUser(
			@RequestParam(value = "loginId", required = true) String loginId,
			@RequestParam(value = "loginPwd", required = true) String loginPwd)
			throws Exception {
		try {
			if ((loginId == null) || "".equals(loginId))
				return JsonUtil.toFailJson(MsgCode.PARAMS_ERROR,
						MsgCode.MSG_PARAMS_USER_LOGINID_ERROR);
			if ((loginPwd == null) || "".equals(loginPwd))
				return JsonUtil.toFailJson(MsgCode.PARAMS_ERROR,
						MsgCode.MSG_PARAMS_USER_LOGINPWD_ERROR);

			User user = new User();
			user.setLoginId(loginId);
			user.setLoginPwd(loginPwd);
			userService.saveUser(user);
			LOGGER.info("id==" + user.getId());

			return JsonUtil.toJson("saveUser", 1, MsgCode.STATUS_CODE_TRUE,
					null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("saveUser", 0, MsgCode.STATUS_CODE_FALSE,
					null, null);
		}
	}


	/**
	 * 用户信息批量删除
	 * @param idStr 参数以,拼接
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUserByIdStr")
	public @ResponseBody Map<String, Object> deleteUserByIdStr(
			@RequestParam("idStr") String idStr) throws Exception {

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<String> list = new LinkedList<String>();
			String[] idStrArray = idStr.split(",");
			for (int i = 0; i < idStrArray.length; i++) {
				String id = idStrArray[i];
				list.add(id);
			}
			params.put("list", list);
			userService.deleteUserByIdStr(params);

			return JsonUtil.toJson("deleteUserByIdStr", 1,
					MsgCode.STATUS_CODE_TRUE, null, null);
		} catch (Exception e) {
			return JsonUtil.toJson("deleteUserByIdStr", 0,
					MsgCode.STATUS_CODE_FALSE, null, null);
		}
	}

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUser(@RequestParam("id") int id)
			throws Exception {
		User user = null;
		try {
			user = userService.getUser(id);

			return JsonUtil.toJson("getUser", user, MsgCode.STATUS_CODE_TRUE,
					null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("getUser", user, MsgCode.STATUS_CODE_FALSE,
					null, null);
		}
	}

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserByIdResultMap")
	public @ResponseBody Map<String, Object> getUserByIdResultMap(
			@RequestParam("id") int id) throws Exception {
		User user = null;
		try {
			user = userService.getUserById(id);

			return JsonUtil.toJson("getUserByIdResultMap", user,
					MsgCode.STATUS_CODE_TRUE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("getUserByIdResultMap", user,
					MsgCode.STATUS_CODE_FALSE, null, null);
		}
	}

	/**
	 * 根据用户ID查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserById")
	public @ResponseBody Map<String, Object> getUserById(
			@RequestParam("id") int id) throws Exception {
		Map<String, Object> map = null;
		try {
			map = userService.getUserByIdResultMap(id);

			return JsonUtil.toJson("getUserById", map,
					MsgCode.STATUS_CODE_TRUE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("getUserById", map,
					MsgCode.STATUS_CODE_FALSE, null, null);
		}
	}

	/**
	 * 获取用户信息集合
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserList")
	public @ResponseBody Map<String, Object> queryUserList(
			@RequestParam("id") int id) throws Exception {
		List<Map<String, Object>> list = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		try {
			list = userService.queryUserList(params);

			return JsonUtil.toJson("queryUserList", list,
					MsgCode.STATUS_CODE_TRUE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("queryUserList", list,
					MsgCode.STATUS_CODE_FALSE, null, null);
		}
	}

	/**
	 * 获取用户信息集合
	 * @param loginId
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserListReturnUser")
	public @ResponseBody Map<String, Object> queryUserListReturnUser(
			@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "userName", required = false) String userName)
			throws Exception {
		List<User> list = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginId", loginId);
		params.put("userName", userName);
		try {
			list = userService.queryUserListReturnUser(params);

			return JsonUtil.toJson("queryUserListReturnUser", list,
					MsgCode.STATUS_CODE_TRUE, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonUtil.toJson("queryUserListReturnUser", list,
					MsgCode.STATUS_CODE_FALSE, null, null);
		}
	}
	
	
	
	
	
	
	
//	TestToolService testToolService = ServiceLocator.getInstance().getTestToolService();
//	
//	OrderToolService orderToolService = ServiceLocator.getInstance().getOrderToolService();
//	
//	@RequestMapping(value = "/sayHello")
//	public @ResponseBody Map<String, Object> sayHello(
//			@RequestParam(value = "userName", required = true) String userName)
//			throws Exception {
//		try {
//			String result=testToolService.sayHello(userName);
//		    System.out.println("result=="+result);
//		    
//		    List<Map<String, Object>> list=orderToolService.queryUserList(null);
//	        System.out.println("size=="+list.size());
//
//			return JsonUtil.toJson("sayHello", 1, MsgCode.STATUS_CODE_TRUE,
//					null, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return JsonUtil.toJson("sayHello", 0, MsgCode.STATUS_CODE_FALSE,
//					null, null);
//		}
//	}
}
