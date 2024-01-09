package com.统一返回和全局异常返回.统一API返回;

import lombok.Data;

/**
 * 错误码对象
 *
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，
 * // 模块 infra 错误码区间 [1-001-000-000 ~ 1-002-000-000)
 *     // 模块 system 错误码区间 [1-002-000-000 ~ 1-003-000-000)
 *     // 模块 report 错误码区间 [1-003-000-000 ~ 1-004-000-000)
 *     // 模块 member 错误码区间 [1-004-000-000 ~ 1-005-000-000)
 *     // 模块 mp 错误码区间 [1-006-000-000 ~ 1-007-000-000)
 *     // 模块 pay 错误码区间 [1-007-000-000 ~ 1-008-000-000)
 *     // 模块 product 错误码区间 [1-008-000-000 ~ 1-009-000-000)
 *     // 模块 bpm 错误码区间 [1-009-000-000 ~ 1-010-000-000)
 *     // 模块 trade 错误码区间 [1-011-000-000 ~ 1-012-000-000)
 *     // 模块 promotion 错误码区间 [1-013-000-000 ~ 1-014-000-000)
 *
 * TODO 错误码设计成对象的原因，为未来的 i18 国际化做准备
 */
@Data
public class ErrorCode {

    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
