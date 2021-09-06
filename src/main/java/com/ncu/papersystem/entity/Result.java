package com.ncu.papersystem.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 202105091413L;


    public static final String STATE = "state";

    public static final String NULL = "";

    public static final int SUCCESS_CODE = 10000;
    public static final String SUCCESS_MESSAGE = "操作成功";

    public static final int FAIL_CODE = 10001;
    public static final String FAIL_MESSAGE = "操作失败";

    public static final int ILLEGAL_ARGS_CODE = 10002;
    public static final String ILLEGAL_ARGS_MESSAGE = "参数异常";

    public static final int SYS_ERROR_CODE = 10003;
    public static final String SYS_ERROR_MESSAGE = "系统内部错误";

    public static final int UNAUTHORIZED_CODE = 10004;
    public static final String UNAUTHORIZED_MESSAGE = "未授权";

    public static final int UNLOGIN_CODE = 10005;
    public static final String UNLOGIN_MESSAGE = "用户未登录";

    public static final int UNSUPPORTED_METHOD_CODE = 10006;
    public static final String UNSUPPORTED_METHOD_MESSAGE = "错误请求类型";

    public static final int UNSUPPORTED_MEDIA_TYPE_CODE = 10007;
    public static final String UNSUPPORTED_MEDIA_TYPE_MESSAGE = "错误媒体类型";

    public static final int UNKNOWN_CODE = 10008;
    public static final String UNKNOWN_MESSAGE = "未知错误";

    public static final int SERVICE_BUSY_CODE = 10009;
    public static final String SERVICE_BUSY_MESSAGE = "服务繁忙,请稍后尝试";

    public static final int FORBIDDEN_IP_CODE = 10010;
    public static final String FORBIDDEN_IP_MESSAGE = "访问过于频繁,请稍后尝试";


    private int code;

    private String message;

    private T result;


    public Result() {

    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.result = (T) JSON.parse("{}");
    }

    public Result(int code, String message, T result) {
        this.code = code;
        this.message = message;
        if (result == null) {
            this.result = (T) JSON.parse("{}");
        }else {
            this.result = result;
        }
    }

}
