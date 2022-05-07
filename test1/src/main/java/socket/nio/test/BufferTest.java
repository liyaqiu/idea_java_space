package socket.nio.test;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        FileInputStream fis = new FileInputStream("D:/abc.txt");
        FileChannel inChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("D:/abc1.txt");
        FileChannel outChannel = fos.getChannel();


        ByteBuffer[] byteBuffer  =new ByteBuffer[2];
        byteBuffer[0] = ByteBuffer.allocate(2);
        byteBuffer[1] = ByteBuffer.allocate(10);


        byte[] bytes  =new byte[(int)inChannel.size()];
        long len = 0;
        while ((len = inChannel.read(byteBuffer))!=-1){
            Arrays.asList(byteBuffer).stream().forEach(bf -> bf.flip());
            outChannel.write(byteBuffer);
            Arrays.asList(byteBuffer).stream().forEach(bf -> bf.clear());
        };


        fis.close();
        fos.close();

    }

    public void test4() throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:/abc.txt", "rw");
//        byte[] bytes1 = "你们都是好孩子".getBytes(StandardCharsets.UTF_8);
//        System.out.println(bytes1.length);
//        System.out.println(Arrays.toString(bytes1));
//        randomAccessFile.write(bytes1);

        FileChannel inChannel = randomAccessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int)inChannel.size());

        inChannel.read(buffer);

        System.out.println(new String(buffer.array(),StandardCharsets.UTF_8));
        randomAccessFile.close();
    }

    public void test3(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        for (int i = 0; i <10 ; i++) {
            byteBuffer.put(Byte.valueOf((i+'a')+""));
        }

        System.out.println(Arrays.toString(byteBuffer.array()));

        byteBuffer.flip();
        ByteBuffer buffer = byteBuffer.asReadOnlyBuffer();
        while (buffer.hasRemaining()){
            System.out.print(buffer.get()+"  ");
        }
        System.out.println();
        //buffer.clear();
        buffer.position(0);
        while (buffer.hasRemaining()){
            System.out.print(buffer.get()+"  ");
        }
    }

    public void test2(){
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putChar('a');
        buffer.putChar('a');
        buffer.putChar('我');
        System.out.println(buffer.array().length);
        ByteBuffer allocate = ByteBuffer.allocate(6);
        System.out.println(Arrays.toString(buffer.array()));
        allocate.put(buffer.array());
        System.out.println(Arrays.toString(allocate.array()));
        allocate.flip();
        System.out.println(allocate.getChar());
        System.out.println(allocate.getChar());
        System.out.println(allocate.getChar());
    }


    public void test1(){
        IntBuffer intBuffer = IntBuffer.allocate(3);
        intBuffer.put(5);
        intBuffer.put(6);
        intBuffer.put(7);
//        intBuffer.position(0);
//        intBuffer.limit(2);
        intBuffer.clear();

        System.out.println("-->"+intBuffer.put(0,1));
        System.out.println("-->"+intBuffer.put(1,2));
        System.out.println("-->"+intBuffer.put(1,3));
        System.out.println("-->"+intBuffer.put(1,4));
        System.out.println("-->"+intBuffer.get(1));
        System.out.println("-->"+intBuffer.get(1));
        System.out.println("-->"+intBuffer.get(0));
        System.out.println("-->"+intBuffer.get(2));
        System.out.println("-->"+intBuffer.get(0));
        System.out.println("-->"+intBuffer.get(2));
        System.out.println("-------------------------------");
        System.out.println("-->"+intBuffer.get());
        System.out.println("-->"+intBuffer.get());
        System.out.println("-->"+intBuffer.get());
//        intBuffer.clear();
//        System.out.println("-->"+intBuffer.get());
//        System.out.println("-->"+intBuffer.get());

//        System.out.println(Arrays.toString(intBuffer.array()));
        intBuffer.flip();
        //System.out.println("-->"+intBuffer.get());
        /**
         *  limit = position;
         *   position = 0;
         * */
        intBuffer.flip();
        intBuffer.put(3);
        // intBuffer.put(4);

        intBuffer.flip();
        System.out.println("-->"+intBuffer.get());
        System.out.println("-->"+intBuffer.get());
//        System.out.println(intBuffer.hasRemaining());
//        System.out.println("-->"+intBuffer.get());
//
//        System.out.println(Arrays.toString(intBuffer.array()));
    }
}
