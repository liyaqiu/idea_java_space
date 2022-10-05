package com.gzzn.service.utils;

/**
 * @author eric
 * @date 2022/9/3 20:46
 * http响应统一返回
 **/
public final class Res {
    private boolean success;
    private int code;
    private String message;
    private Object data;

    private Res(){}

    public static Res ok(){
        Res r = new Res();
        r.setSuccess(true);
        r.setCode(ResCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }
    public static Res fail(){
        Res r = new Res();
        r.setSuccess(false);
        r.setCode(ResCode.FAIL);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public Res setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Res setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Res setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Res setData(Object data) {
        this.data = data;
        return this;
    }
}
