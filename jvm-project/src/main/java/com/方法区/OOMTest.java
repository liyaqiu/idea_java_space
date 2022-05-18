package com.方法区;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/5/14 12:07
 * 实现同一个class文件不同加载器加载
 * 实现动态加载类
 * 可以对class做加密处理
 *  https://www.bilibili.com/video/BV1PJ411n7xZ?p=91
 * -ea -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails
 **/
public class OOMTest extends ClassLoader{

    /**
     * 双亲委派机制
     * */
    /*@Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }*/

    /**
     * 非双亲委派机制
     * */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:\\idea_java_space\\jvm-project\\src\\main\\resources\\PersonEntity.class");
        try(FileInputStream inputStream = new FileInputStream(file);){
            byte[] bytes = new byte[(int) file.length()];
            int len = inputStream.read(bytes);
            //可以实现对流的加密和解密操作
            return defineClass(name,bytes,0,len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void test1() throws Exception {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(new OOMTest().findClass("com.ClassLoader.custom.PersonEntity"));
        }
    }
}
