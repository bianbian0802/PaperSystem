package com.ncu.papersystem.entity;

import cn.hutool.core.util.StrUtil;

/**
 * @author Xiongwb
 * @date 2021/5/9 14:15
 */

public class ResultWrapper {
    private ResultWrapper(){

    }

    public static <T> Result<T> success() {
        return new Result<>(Result.SUCCESS_CODE, Result.SUCCESS_MESSAGE);
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(Result.SUCCESS_CODE, message);
    }

    public static <T> Result<T> success(T o) {
        return new Result<>(Result.SUCCESS_CODE, Result.SUCCESS_MESSAGE, o);
    }

    public static <T> Result<T> success(String message, T o) {
        return new Result<>(Result.SUCCESS_CODE, message, o);
    }

    public static <T> Result<T> wrap(int code, String message, T o) {
        return new Result<>(code, message, o);
    }

    public static <T> Result<T> wrap(int code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> error() {
        return wrap(Result.FAIL_CODE, Result.FAIL_MESSAGE);
    }

    public static <T> Result<T> error(String message) {
//        return wrap(Result.FAIL_CODE, StringUtils.isBlank(message) ? Result.FAIL_MESSAGE : message);
        return wrap(Result.FAIL_CODE, StrUtil.isNotBlank(message) ? message : Result.FAIL_MESSAGE);
    }

    public static <T> Result<T> illegalArgs(String message) {
        return wrap(Result.ILLEGAL_ARGS_CODE, message);
    }

}
