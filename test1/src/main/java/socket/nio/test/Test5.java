package socket.nio.test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test5 {
    public static void main(String[] args) throws Exception {



        File file = new File("d://a");
        Files.walkFileTree(Paths.get("d://a"), new SimpleFileVisitor(){
            @Override
            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                Path path  = (Path) file;
                Files.delete(path);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
                System.out.println(dir);
                Path path  = (Path) dir;
                Files.delete(path);
                return super.postVisitDirectory(dir, exc);
            }
        });
//        ByteBuffer buffer = ByteBuffer.allocate(10);
//
//        buffer.put("abcde".getBytes(StandardCharsets.UTF_8));
//        buffer.flip();
//        System.out.println(StandardCharsets.UTF_8.decode(buffer).toString());
//        buffer.rewind();
//        System.out.println(StandardCharsets.UTF_8.decode(buffer).toString());
//
//        ByteBuffer buffer1 = ByteBuffer.wrap("abcde".getBytes(StandardCharsets.UTF_8));
//        //buffer.flip();
//        System.out.println(StandardCharsets.UTF_8.decode(buffer1).toString());
//        buffer1.rewind();
//        System.out.println(StandardCharsets.UTF_8.decode(buffer1).toString());

        //1048576000
        //
        //direct1();
        //directto();

        Files.copy(Paths.get("D:/abc0.txt"), Paths.get("D:/abc2.txt"), StandardCopyOption.REPLACE_EXISTING);
//        byte[] bytes = new byte[len];
//        for (int i = 0; i <len ; i++) {
//            bytes[i] = 'a';
//        }
//        for (int i = 0; i <10 ; i++) {
//            direct(bytes);
//            heap(bytes);
//            System.out.println("------------------");
//        }
    }
    public static void directto() throws Exception {

        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        FileChannel fileChannel = new RandomAccessFile("D:/abc1.txt", "r").getChannel();
        FileChannel outChannel = new RandomAccessFile("D:/abc2.txt", "rw").getChannel();

        long size = fileChannel.size();
        for (long left =size ; left >0 ; ) {
            long len = fileChannel.transferTo(size-left, left, outChannel);
            left = left - len;
        }

//        long len = fileChannel.transferTo(0, fileChannel.size(), outChannel);
//        long yu = fileChannel.size() - len;
//        fileChannel.transferTo(len, yu, outChannel);

        outChannel.close();
        fileChannel.close();
        endTime = System.currentTimeMillis();
        System.out.println("direct:-->"+(endTime-startTime));
    }
    public static void direct1() throws Exception {
        int len = 1024*1024*2000;
        System.out.println(len+"----");
        ByteBuffer buffer = ByteBuffer.allocate(len);
        for (int i = 0; i <len ; i++) {
            buffer.put((byte)97);
        }

        buffer.flip();
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/abc1.txt", "rw");

        randomAccessFile.seek(randomAccessFile.length());
        FileChannel fileChannel = randomAccessFile.getChannel();
        System.out.println(fileChannel.write(buffer));
        fileChannel.close();
        endTime = System.currentTimeMillis();
        System.out.println("direct:-->"+(endTime-startTime));
    }
    public static void direct(byte[] bytes) throws Exception {
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/abc1.txt", "rw");

        randomAccessFile.write(bytes);
        randomAccessFile.close();
        endTime = System.currentTimeMillis();
        System.out.println("direct:-->"+(endTime-startTime));
    }

    public static void heap(byte[] bytes) throws Exception {
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream("D:/abc2.txt");
        fos.write(bytes);
        fos.close();
        endTime = System.currentTimeMillis();
        System.out.println("heap:-->"+(endTime-startTime));
    }
}
