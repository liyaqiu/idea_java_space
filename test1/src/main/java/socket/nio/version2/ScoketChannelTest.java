package socket.nio.version2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ScoketChannelTest {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress("127.0.0.1",9998));
        System.out.println("通过"+sc);

        int capacity = 1024*1024*100;
//        ByteBuffer buffer  = ByteBuffer.allocate(capacity);
//        for (int i = 0; i <capacity ; i++) {
//            buffer.put((byte)'a');
//        }
//        buffer.flip();
        while (true){
            new Scanner(System.in).next();
            //sc.read(ByteBuffer.allocate(1024*1024*10));
//            while (buffer.hasRemaining()) {
//                //阻塞情况下

                int len = sc.write(StandardCharsets.UTF_8.encode("李雅秋"));
                System.out.println(len);
                System.out.println("22222222222222");
//            }
        }
    }
}
