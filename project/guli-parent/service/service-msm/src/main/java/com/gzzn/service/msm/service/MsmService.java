package com.gzzn.service.msm.service;

import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface MsmService {

    Map<String, Object> sendCode(String mobile);

    void verifyCode(String mobile, String code);
}
