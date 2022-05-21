package com;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
public class Test {

   String str = "你哈搜";


    public static void  main(String[] args) throws InterruptedException {
      //-Xms300m -Xmx600m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=100m
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
     /* String str0 = "我们";
      String str1 =str0+"hello";
      String str2 ="hello";
      String str4 ="我们hello";*/
     // String s1 = new String("123");

        /*String a1 = new String("123");
        String a2 = "123";*/


     /*  char[] ch =  new char[]{'1','2','3','4','5','6'};
       String s1 =  new String(ch, 0, ch.length).intern();
       String d = "123456";*/

       /*String s2 = new String("654321");
       String s4 = "654321";*/

       /* String s1 = new String("123");
        s1.intern();*/

        /*String s22 = new String("1");
        s22.intern();
        String s44 = "1";
        System.out.println(s22==s44);

        char[] ch =  new char[]{'1','1'};
        String s2 =  new String(ch, 0, ch.length);
        //String s2 = new String("1")+new String("1");
        s2.intern();
        String s4 = "11";
        System.out.println(s2==s4);*/

        String s1 = new String("13");

       /* String s = String.valueOf("123");

        String s2 = new String("12")+new String("3");
        //s2.intern();
        String s3 = "123";
        System.out.println(s2 == s3);*/
    /*  final String a1 ="123";
       String a2 ="456";
       String a3 ="567";
       String s4 = "123456567";
       String s5 = a1+a2;*/
   }
}
