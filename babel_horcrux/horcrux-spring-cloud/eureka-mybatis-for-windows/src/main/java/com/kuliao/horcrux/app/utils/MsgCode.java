package com.kuliao.horcrux.app.utils;

/**
 * Created by zc on 12/27/17.
 * @author zc
 *
 */
public class MsgCode {

    /**
     * header statusCode
     */
    public static final String STATUS_CODE_TRUE = "1";// 正确
    public static final String STATUS_CODE_FALSE = "0";// 错误

    /**
     * 不需要返回时值时，只需要返回一个成功或者失败状态
     * eg:当插入成功时，返回“1”，反之“0”
     */
    public static final String BODY_RETURN_TRUE = "1";
    public static final String BODY_RETURN_FALSE = "0";
    
    /**
     * 消息类型
     */
    public static final String CODE_USER_LOGIN = "100";// 登录注册
    public static final String REQUEST_ERROR = "101";// 请求失败
    public static final String PARAMS_ERROR = "102";// 参数传递错误
    public static final String AUTHEN_ERROR = "1103";// 认证失败
    public static final String ACCESS_DENIED = "1104";// 没有权限

    public static final String MSG_USER_EXSIT = "账户名不存在，请重新输入";
    public static final String MSG_PWD_ERROR = "账户名与密码不匹配，请重新输入";
    public static final String MSG_REGISTER_ERROR = "账户名已存在，请重新输入";
    public static final String MSG_MSMCODE_ERROR = "短信验证码错误";
    public static final String MSG_PARAMS_ERROR = "参数传递错误";
    
    public static final String MSG_PARAMS_USER_LOGINID_ERROR = "登陆账号不能为空";
    public static final String MSG_PARAMS_USER_LOGINPWD_ERROR = "登陆账号密码不能为空";

    /**
     * 请求失败类型信息
     */
    public static final String MSG_REQUEST_ERROR = "请求失败";

    /**
     * body错误信息
     */
    public static final String BODY_REQUEST_ERROR = "请求失败";

    /**
     * 客户端请求成功CODE
     */
    public static final String SUCCEED_CODE = "100";

    /**
     * 客户端请求成功MSG
     */
    public static final String SUCCEED_MSG = "请求成功";

}
