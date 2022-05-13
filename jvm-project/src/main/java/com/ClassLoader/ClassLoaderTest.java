package com.ClassLoader;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author eric
 * @date 2022/5/13 16:17
 **/
@Slf4j
public class ClassLoaderTest {
    @Test
    public void test(){

        //系统类加载器 sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        log.info("获取系统类加载器: {}",systemClassLoader);

        //扩展类加载器 sun.misc.Launcher$ExtClassLoader@4f023edb
        ClassLoader extClassLoader = systemClassLoader.getParent();
        log.info("获取扩展类加载器: {}",extClassLoader);

        //引导类加载器 null
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        log.info("获取引导类加载器: {}",bootstrapClassLoader);

        //用户类是系统类加载器加载  sun.misc.Launcher$AppClassLoader@18b4aac2
        log.info("ClassLoaderTest: {}",ClassLoaderTest.class.getClassLoader());

        //String类是引导类加载器加载 null
        log.info("String: {}",String.class.getClassLoader());
    }
}
