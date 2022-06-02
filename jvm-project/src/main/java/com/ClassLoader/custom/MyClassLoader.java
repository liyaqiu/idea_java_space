package com.ClassLoader.custom;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author eric
 * @date 2022/5/14 12:07
 * 实现同一个class文件不同加载器加载
 * 实现动态加载类
 * 可以对class做加密处理
 **/
public class MyClassLoader extends ClassLoader{

    public MyClassLoader(){
        super();
    }

    protected MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    /*@Test
    public void test61(){
        MyClassLoader myClassLoader = new MyClassLoader(new MyClassLoader());
        System.out.println(myClassLoader.getParent().getParent());
    }*/

    /**
     * 不经过双亲委派机制校验
     * */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        name = name.replace("###", "");
        File file = new File("D:\\idea_java_space\\jvm-project\\src\\main\\resources\\User.class");
        try(FileInputStream inputStream = new FileInputStream(file);){
            byte[] bytes = new byte[(int) file.length()];
            int len = inputStream.read(bytes);
            //可以实现对流的加密和解密操作
            //return defineClass(null,bytes,0,len);//如果名字为空，则会自动获取com.ClassLoader.custom.User
            return defineClass("com.ClassLoader.custom.User",bytes,0,len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 经过了双亲委派机制校验
     * */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Test
    public void test1() throws Exception {
        Class<?> aClass = new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity");
        Class<?> bClass = new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity");
        System.out.println(aClass);
        System.out.println(bClass);
        System.out.println(aClass.getClassLoader());
        System.out.println(aClass.getClassLoader().getParent());
    }

    @Test
    public void test2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        new MyClassLoader().findClass("com.ClassLoader.custom.PersonEntity").newInstance();
        //new MyClassLoader().loadClass("###com.ClassLoader.custom.PersonEntity").newInstance();

        //System.out.println(new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity").getClassLoader());

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.loadClass("com.ClassLoader.custom.User");
        myClassLoader.loadClass("com.ClassLoader.custom.User");


      /*  Father f = (Father) new MyClassLoader().loadClass("com.ClassLoader.custom.User").newInstance();
        Father f1 = (Father) new MyClassLoader().loadClass("com.ClassLoader.custom.User").newInstance();
        f.test();
        f1.test();
        System.out.println(f);
        System.out.println(f1);
        System.out.println(Father.class.getClassLoader());
        System.out.println(f.getClass().getClassLoader());
        System.out.println(f1.getClass().getClassLoader());*/
    }


    public static void main1(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        /*Class<?> clzss = Class.forName("com.ClassLoader.custom.PersonEntity", true, new MyClassLoader());
        Object obj = clzss.newInstance();
        System.out.println(obj.getClass());*/
    }

    @Test
    public void test5() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        /*Constructor<?>[] constructors = User.class.getDeclaredConstructors();
        Constructor<?> constructor = constructors[0];
        constructor.setAccessible(true);
        System.out.println(constructor.newInstance());*/
    }

    @Test
    public void test6(){
        System.out.println(MyClassLoader.class.getClassLoader().getResourceAsStream("com/ClassLoader/custom/123.txt"));
        Thread thread = Thread.currentThread();
        System.out.println(thread.getClass().getClassLoader());
    }
}

