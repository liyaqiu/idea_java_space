package com.sfkj.service.hospital.api.utils;

/**
 * @author eric
 * @date 2022/11/22 19:02
 **/
public interface ResCode {
    int SUCCESS = 20000;
    int FAIL = 40000;
    int BUSINESS_FAIL = 40001;
    int DATA_VALIDATION_FAIL = 40002;
    int FILE_UPLOAD_FAIL = 40003;

    int UNAUTHORIZED = 50006;

    int NO_LOGIN = 50005;
    int ILLEGAL_TOKEN = 50008;
    int OTHER_CLIENTS_LOGGED_IN = 50012;
    int TOKEN_EXPIRED = 50014;
}
