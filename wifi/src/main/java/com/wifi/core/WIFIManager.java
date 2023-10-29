package com.wifi.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.wifi.config.WIFIProfile;
import com.wifi.constant.PathConstant;
import com.wifi.constant.WIFIConstant;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author eric
 * @date 2023/10/22 2:20
 **/
@AllArgsConstructor
public class WIFIManager {

    private String realName = "Xiaomi_1034";

    private String password = "18813664703";

    public boolean execute(){
        //生成配置
        String wifiProfile = new WIFIProfile(password,realName, password).generate();
        //文件输出的路径
        String filePath = PathConstant.PROFILE_PATH+password+".xml";
        //把文件写出磁盘
        FileUtil.writeUtf8String(wifiProfile, filePath);

        //添加配置文件
        if (!exec(WIFIConstant.ADD_PROFILE.replace("#path#", filePath),"已将配置文件")) {
            throw new RuntimeException("添加配置文件失败");
        }

        if (!exec(WIFIConstant.CONNECT_PROFILE.replace("#displayName#", password),"已成功完成连接请求")) {
            throw new RuntimeException("连接配置文件失败");
        }

        ThreadUtil.sleep(1000);
        if (exec("ipconfig",(result) -> {
            if(result.split("无线局域网适配器")[1].contains("默认网关")){
                  return true;
            }
            return false;
        })) {
            System.out.println("wifi账户为："+realName+"  密码为："+password);
            return true;
        }

//        if (exec("ping 8.8.8.8","TTL")) {
//            System.out.println("wifi账户为："+realName+"  密码为："+password);
//            return true;
//        }

        if (!exec(WIFIConstant.DELETE_PROFILE.replace("#displayName#", password),"删除配置文件")) {
            throw new RuntimeException("删除配置文件失败");
        }

        return false;
    }

    private boolean exec(String cmd, Function<String,Boolean> function){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            return function.apply(IoUtil.read(process.getInputStream(), Charset.forName("gbk")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean exec(String cmd,String successFlag){
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            String result = IoUtil.read(process.getInputStream(), Charset.forName("gbk"));
            //System.out.println(result);
            if (result.contains(successFlag)) {
                return true;
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
