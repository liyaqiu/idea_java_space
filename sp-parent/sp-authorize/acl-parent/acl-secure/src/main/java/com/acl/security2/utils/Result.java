package com.acl.security2.utils;

import lombok.Data;

/**
 * @author lyq
 * @date 2021/12/7 10:59
 */
@Data
public class Result {
    private int state;
    private boolean isSuccess;
    private String message;
    private Object data;

    public static final int OK = 200;
    public static final int FAIL = 400;
    public static final int SENTINEL_FAIL = 429;

    public Result(int state, String message, boolean isSuccess, Object data) {
        this.state = state;
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public Result(String message, int state) {
        this.state = state;
        this.isSuccess = false;
        this.message = message;
    }
    public static Result fail(Object data){
        return new Result(Result.OK,"成功",true,data);
    }

    public static Result ok(Object data){
        return new Result(Result.FAIL,"失败",true,data);
    }

}
