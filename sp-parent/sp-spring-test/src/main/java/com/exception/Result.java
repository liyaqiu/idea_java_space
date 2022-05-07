package com.exception;

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

    public Result(int state, String message,boolean isSuccess, Object data) {
        this.state = state;
        this.isSuccess = isSuccess;
        this.data = data;
        this.message = message;
    }

    public Result(String message,int state) {
        this.state = state;
        this.isSuccess = false;
        this.message = message;
    }
}
