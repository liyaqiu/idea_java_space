package socket.nio.version1;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.spi.AbstractSelectionKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
@Slf4j
public class ServerSockerChannelTest {
    private static void printSelectorInfo(Selector selector){
        System.out.println(selector.hashCode());
        System.out.println("selector.keys():"+selector.keys().size());
        for (SelectionKey key : selector.keys()) {
            if(key.channel() instanceof SocketChannel){
                System.out.println("客户端channel:"+key.channel().getClass()+"  "+key.hashCode());
            }else{
                System.out.println("服务器channel:"+key.channel().getClass()+"  "+key.hashCode());
            }

        }
        System.out.println("selector.selectedKeys():"+selector.selectedKeys().size());
        System.out.println("------------------------------------");
    }

    public static void main(String[] args) throws Exception{
        log.info("123");
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        //ssc.socket().bind(new InetSocketAddress("0.0.0.0",9998));
        ssc.bind(new InetSocketAddress("0.0.0.0",9998),2);

        Selector selector = Selector.open();
        printSelectorInfo(selector);
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        printSelectorInfo(selector);
        while (true){
            System.out.println("监听....");
            //只有读事件和连接事件才会阻塞
            System.out.println(selector.select());
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            printSelectorInfo(selector);
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    System.out.println("有连接");
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_WRITE);
                    printSelectorInfo(selector);
                }
                if(selectionKey.isWritable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.write(ByteBuffer.wrap("你好客户端->".getBytes(StandardCharsets.UTF_8)));
                    printSelectorInfo(selector);
                    channel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    ByteBuffer buffer = ByteBuffer.allocate(6);
                    int len = channel.read(buffer);
                    buffer.flip();
                    System.out.println("读到的数据->"+new String(buffer.array(),0,len,"utf-8"));
                    //buffer.clear();
                }
                iterator.remove();
            }
            printSelectorInfo(selector);
            Thread.sleep(2000);
        }

    }
}
