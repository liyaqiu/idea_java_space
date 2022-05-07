package socket.nio.test;

import java.io.*;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ChannelTest {
    public static void main(String[] args) throws Exception{

        byte[] bytes = new byte[1024*1024*100];
        for (int i = 0; i <1024*1024*100 ; i++) {
            bytes[i] = 'a';
        }
        for (int i = 0; i <3 ; i++) {
            nio(bytes);
            bio(bytes);
            bio1(bytes);
            System.out.println("------------------");
        }
//        System.out.println(Charset.defaultCharset());
//
//        //nio("我们都是好孩子".getBytes(StandardCharsets.UTF_8));
//        FileInputStream fis = new FileInputStream("D:/abc.txt");
//        FileChannel inChannel = fis.getChannel();
//        FileOutputStream fos = new FileOutputStream("D:/abc1.txt");
//        FileChannel outChannel = fos.getChannel();

//        ByteBuffer buffer = ByteBuffer.allocate(3);
//
//        int len = 0;
//        while ((len = inChannel.read(buffer))!=-1){
//            buffer.flip();
//            outChannel.write(buffer);
//            //System.out.print(new String(buffer.array(),0,len));
//            buffer.clear();
//        }
//
//        inChannel.transferTo(0, inChannel.size(), outChannel);
//        //outChannel.transferFrom(inChannel, 0, inChannel.size());
//
//        fis.close();
//        fos.close();
//
//        ServerSocket socket = new ServerSocket();
//        ServerSocketChannel serverSocketChannel = socket.getChannel();
//        ServerSocketChannel open = ServerSocketChannel.open();
//
//        open.socket().bind(null);
        //this.socket = ServerSocketAdaptor.create(this);
    }

    public static void nio(byte[] bytes) throws Exception {
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();


        FileOutputStream fos = new FileOutputStream("D:/abc.txt");
        FileChannel channel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024*1024*100);
        buffer.put(bytes);
        buffer.flip();
        channel.write(buffer);
        fos.close();
        endTime = System.currentTimeMillis();
        System.out.println("nio:-->"+(endTime-startTime));
    }

    public static void bio(byte[] bytes) throws Exception {
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream("D:/abc1.txt");
        fos.write(bytes);
        fos.close();
        endTime = System.currentTimeMillis();
        System.out.println("bio:-->"+(endTime-startTime));
    }

    public static void bio1(byte[] bytes) throws Exception {
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("D:/abc2.txt"));
        fos.write(bytes);
        fos.close();
        endTime = System.currentTimeMillis();
        System.out.println("bio1:-->"+(endTime-startTime));
    }

}
