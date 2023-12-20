package io;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Properties;

public class Test22222 {
    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {


//        Test22222 test = new Test22222();
//        //test.test11();
//
//        PrintStream out = System.out;
//        out.println("123");
//        out.close();
//        System.out.println("456");
//
//        for (int i = 0; i <10 ; i++) {
//            System.out.println();
//        }



//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out, "utf-8"));
//        writer.write("123");
//        writer.flush();
        System.out.println(Charset.defaultCharset());
        Properties properties = new Properties();
        properties.put("name", "liyaqiu");
        properties.put("age",12);
//        properties.list(new PrintWriter(new FileWriter("E:\\3.txt"), true));
//        properties.store(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\2.txt"),"utf-8")), "nihao");

//      BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(""));
//      bufferedWriter.newLine();
        System.out.print("111");

    }

    public void test11() throws Exception{
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\1.jpg"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] b = new byte[100];
        int len = 0;
        while ((len = bis.read(b)) != -1) {
            baos.write(b,0,len);
        }
        //bos.flush();

        System.out.println(baos.toByteArray().length);


    }




    public void test10() throws  Exception{

        System.out.println(Charset.defaultCharset());
        FileInputStream fileInputStream  = new FileInputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\22.txt");
        byte[] b = new byte[30];

        int len = fileInputStream.read(b);
        System.out.println(Arrays.toString(b));
        System.out.println(new String(b,0,len,"utf-8"));
    }
    public void test1() throws Exception {
        FileInputStream bis =  new FileInputStream("E:\\1.txt");
        FileOutputStream fos = new FileOutputStream("E:\\2.txt");
        int len = 0;
        byte[] b = new byte[10];
        while ((len = bis.read(b)) != -1) {
            fos.write(b,0,len);
        }//[-2, -1, 0, 49, 0, 0, 0, 0, 0, 0]
        //[-17, -69, -65, 49, 0, 0, 0, 0, 0, 0]
        System.out.println(Arrays.toString(b));
        bis.close();
        fos.close();

    }

    //写一个utf-8编码文件出去
    public void test9() throws  Exception{
        System.out.println(Charset.defaultCharset());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\22.txt"), Charset.forName("utf-8")));
        bw.write("aaaa我");
        bw.close();
    }

    public void test8() throws  Exception{
        System.out.println(Charset.defaultCharset());
        BufferedReader br = new BufferedReader(new FileReader("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\11.txt"));
        System.out.println(br.readLine());
    }
    public void test7() throws  Exception{
        //文件是gbk编码 -->读进来gbk，在用reader转成utf-8编码
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\11.txt"),"gbk"));
        System.out.println(br.readLine());
    }

    public void test6() throws  Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\a.data"));
        Person person = (Person) ois.readObject();
        Person person1 = (Person) ois.readObject();
        System.out.println(person);
        System.out.println(person1);
    }

    public void test5() throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\a.data"));
        oos.writeObject(new Person("李雅秋1", 30));
        oos.writeObject(new Person("李雅秋2", 31));
        oos.close();
    }


    public void test3() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("E:\\1.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\3.txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            //bw.newLine();
        }

        bw.flush();
    }

    public void test4() throws  Exception{
        byte[] b = new byte[1024*8+1];
        for (int i = 0; i <b.length ; i++) {
            b[i] = 97;
        }

        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(b));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);


        byte[] bytes = new byte[8];
        int len= 0;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(b,0,len);
        }
        bos.close();
        System.out.println(baos.toByteArray().length);

    }



    public void test2() throws Exception {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\1.mp4"));
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("D:\\QQ\\doc\\361234567\\FileRecv\\MobileFile\\3.mp4"));
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = bis.read(b)) != -1) {
            fos.write(b,0,len);
        }
        bis.close();
        fos.close();
    }
}
