package com;

import java.io.*;

/**
 * @author eric
 * @date 2022/6/1 19:24
 **/
public class Test2 {
    public static void main(String[] args) throws IOException {

        mergeFIle();
    }

    public static void mergeFIle() throws IOException {
        FileInputStream in1 = new FileInputStream("E:\\摄影\\结婚相关\\aa\\1.txt");
        FileInputStream in2 = new FileInputStream("E:\\摄影\\结婚相关\\aa\\2.txt");
        FileInputStream in3 = new FileInputStream("E:\\摄影\\结婚相关\\aa\\3.txt");

        FileOutputStream out = new FileOutputStream("E:\\摄影\\结婚相关\\aa\\marry.mpg",true);

        byte[] MB1 = new byte[1024*1024*1];
        int len = 0;
        long offset = 0;
        while ((len = in1.read(MB1))!=-1){
            out.write(MB1,0,len);
        }
        while ((len = in2.read(MB1))!=-1){
            out.write(MB1,0,len);
        }
        while ((len = in3.read(MB1))!=-1){
            out.write(MB1,0,len);
        }


        in1.close();
        in2.close();
        in3.close();
        out.close();
    }


    public static void splitFIle() throws IOException {
        String outdir = "E:\\摄影\\结婚相关\\aa\\";
        File file = new File("E:\\摄影\\结婚相关\\视频\\李雅秋-冼舒琴2018.11.1.mpg");
        System.out.println(file.length());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] MB1 = new byte[1024*1024*1];
        int len = 0;
        long offset = 0;
        FileOutputStream out1 = new FileOutputStream("E:\\摄影\\结婚相关\\aa\\1.txt");
        FileOutputStream out2 = new FileOutputStream("E:\\摄影\\结婚相关\\aa\\2.txt");
        FileOutputStream out3 = new FileOutputStream("E:\\摄影\\结婚相关\\aa\\3.txt");

        while ((len = fileInputStream.read(MB1))!=-1){
            offset+=len;
            if(offset<2719793834L){
                out1.write(MB1,0,len);
            }else if (offset<5439587668L){
                out2.write(MB1,0,len);
            }else{
                out3.write(MB1,0,len);
            }
        }

        out1.close();
        out2.close();
        out3.close();
        fileInputStream.close();
    }

}
