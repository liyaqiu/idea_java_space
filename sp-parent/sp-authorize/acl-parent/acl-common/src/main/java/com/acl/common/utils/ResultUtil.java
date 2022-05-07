package com.acl.common.utils;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lyq
 * @date 2021/12/7 10:59
 */
@Data
public class ResultUtil {

    public static enum State{
        OK(200,"成功"),
        FAIL(400,"失败"),
        UN_AUTHTICATION(40003,"未认证") ,
        LOGIN_FAIL(40004,"认证失败");

        private final int state;
        private final String message;

        private State(int state, String message) {
            this.state = state;
            this.message = message;
        }

        public int getState(){
            return this.state;
        }
        public String getMessage(){
            return this.message;
        }
    }

    @Getter
    @Setter
    private static class R{
        private int state;
        private boolean isSuccess;
        private String message;
        private Object data;

        public R(int state, boolean isSuccess, String message) {
            this.state = state;
            this.isSuccess = isSuccess;
            this.message = message;
        }

        public R(int state, boolean isSuccess, String message, Object data) {
            this.state = state;
            this.isSuccess = isSuccess;
            this.message = message;
            this.data = data;
        }
    }

    public static R ok() {
        return new R(State.OK.getState(),true,State.OK.getMessage());
    }
    public static String okJson() {
        return JSONUtil.toJsonStr(new R(State.OK.getState(),true,State.OK.getMessage()));
    }
    public static R ok(State state,Object data) {
        return new R(state.getState(),true,state.getMessage(),data);
    }
    public static String okJson(State state,Object data) {
        return JSONUtil.toJsonStr(new R(state.getState(),true,state.getMessage(),data));
    }

    public static R err() {
        return new R(State.FAIL.getState(),true,State.FAIL.getMessage());
    }
    public static String errJson() {
        return JSONUtil.toJsonStr(new R(State.FAIL.getState(),true,State.FAIL.getMessage()));
    }

    public static R err(State state) {
        return new R(state.getState(),true,state.getMessage());
    }
    public static String errJson(State state) {
        return JSONUtil.toJsonStr(new R(state.getState(),true,state.getMessage()));
    }
}
