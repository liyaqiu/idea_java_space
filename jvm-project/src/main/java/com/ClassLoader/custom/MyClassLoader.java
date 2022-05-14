package com.ClassLoader.custom;

import org.junit.Test;
import sun.misc.Launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/5/14 12:07
 * 实现同一个class文件不同加载器加载
 * 实现动态加载类
 * 可以对class做加密处理
 **/
public class MyClassLoader extends ClassLoader{

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
        Class<?> aClass = new MyClassLoader().findClass("com.ClassLoader.custom.PersonEntity");
        Class<?> bClass = new MyClassLoader().findClass("com.ClassLoader.custom.PersonEntity");
        System.out.println(aClass);
        System.out.println(bClass);
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clzss = Class.forName("com.ClassLoader.custom.PersonEntity", true, new MyClassLoader());
        Object obj = clzss.newInstance();
        System.out.println(obj.getClass());
    }
}
