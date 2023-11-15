package com.wifi.constant;

/**
 * @author eric
 * @date 2023/10/22 1:48
 **/
public class WIFIConstant {

    /**
     * netsh wlan add profile filename=D:\wlan\profile\18813664703.xml
     * 添加wifi配置
     * */
    public static final String ADD_PROFILE = "netsh wlan add profile filename=#path#";

    /**
     * 删除wifi配置
     * */
    public static final String DELETE_PROFILE = "netsh wlan delete profile name=#displayName#";

    /**
     * 删除所有wifi配置
     * */
    public static final String DELETE_ALL_PROFILE = "netsh wlan delete profile name=*";

    /*
     * 连接wifi配置
     * */
    public static final String CONNECT_PROFILE = "netsh wlan connect name=#displayName#";
}
