package socket.nio.version1;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SocketChannelTest {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1", 9998))){
            while (!socketChannel.finishConnect()) {
                System.out.println("没连接上可以做别的事情。。。。");
            }
        }
        new Scanner(System.in).next();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int len = socketChannel.read(buffer);
        System.out.println(new String(buffer.array(),0,len));
        buffer.clear();

        while (true){
            new Scanner(System.in).next();
            socketChannel.write(ByteBuffer.wrap("我们都是坏孩子..".getBytes(StandardCharsets.UTF_8)));
            System.out.println("数据已经发送...");
        }

       // Thread.sleep(1000000);

    }
}
