package com.kuliao.horcrux.app.utils;

/**
 * Created by zc on 12/27/17.
 * @author zc
 *
 */
public enum MessageCode {
    //WAITER: 10000
    WAITER_BIND_ERROR("10001", "绑定失败"),

    WAITER_UNBIND_ERROR("10002", "解除绑定失败");



    private final String code;
    private final String message;

    MessageCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
