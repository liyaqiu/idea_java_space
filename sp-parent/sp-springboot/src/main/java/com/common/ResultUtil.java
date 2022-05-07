package com.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lyq
 * @date 2021/12/7 10:59
 */
@Data
public class ResultUtil {

    public static class State{
        public static final int OK = 200;
        public static final int FAIL = 400;
    }

    @Getter
    @Setter
    private static class R{
        private int state;
        private boolean isSuccess;
        private String message;
        private Object data;
    }

    public static R ok() {
        R r = new R();
        r.setState(State.OK);
        r.setSuccess(true);
        return r;
    }

    public static R ok(int state,Object data) {
        R r = new R();
        r.setState(state);
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static R ok(int state,String message, Object data) {
        R r = new R();
        r.setState(state);
        r.setSuccess(true);
        r.setMessage(message);
        r.setData(data);
       return r;
    }


    public R err() {
        R r = new R();
        r.setState(State.FAIL);
        r.setSuccess(false);
        return r;
    }

    public R err(int state) {
        R r = new R();
        r.setState(state);
        r.setSuccess(false);
        return r;
    }

    public R err(int state,String message) {
        R r = new R();
        r.setState(state);
        r.setSuccess(false);
        r.setMessage(message);
        return r;
    }
}
