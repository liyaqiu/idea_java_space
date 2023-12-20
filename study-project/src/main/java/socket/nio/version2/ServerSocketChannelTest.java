package socket.nio.version2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerSocketChannelTest {
    //扩容
    public static ByteBuffer increment(ByteBuffer sourceBuffer){
        ByteBuffer buffer = ByteBuffer.allocate(sourceBuffer.capacity()*2);
        sourceBuffer.flip();
        //不完整的数据不能进行解码，否则出现乱码。要么单个字节放
        //buffer.put(StandardCharsets.UTF_8.decode(sourceBuffer).toString().getBytes(StandardCharsets.UTF_8));
        buffer.put(sourceBuffer);
        return buffer;
    }
    //黏包 和 半包解决
    public static void spiltPack(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if(buffer.get(i)=='\n'){
                //16 i=22  p=0
                //10 i=25  p=16 10
                //10   25  15
                //System.out.println(i);
                int len = i+1- buffer.position();
                ByteBuffer buf = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    buf.put(buffer.get());
                }
                buf.flip();
                //System.out.print("客户端发过来的数据"+StandardCharsets.UTF_8.decode(buf).toString());
            }
        }
        buffer.compact();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("0.0.0.0", 9998));
        System.out.println(ssc.accept());
        System.out.println("进入等待.....");

        SelectionKey sscKey = ssc.register(selector, 0);
        //SelectionKey sscKey1 = ssc.register(selector1, 0);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        //sscKey1.interestOps(SelectionKey.OP_ACCEPT);
        while (true) {
            //事件需要处理了以后，删除才能生效。
            //ssChannel.accept();
            //sc.read(buffer); sc.close(); selectionKey.cancel();
            System.out.println("监听。。。。。");
            //keys的删除需要等到select执行后才能更新个数
            selector.select();
            System.out.println("selector"+selector.hashCode());
            System.out.println("selectedKeys--->:" + selector.selectedKeys().size());
            System.out.println("keys--->:" + selector.keys().size());
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    System.out.println("当前selector"+selectionKey.selector().hashCode());
                    ServerSocketChannel ssChannel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, ByteBuffer.allocate(4000));
                    scKey.interestOps(SelectionKey.OP_WRITE);
                } else if (selectionKey.isReadable()) {
                    read(selectionKey);
                }else if  (selectionKey.isWritable()){
                    //非阻塞情况下写是分段写的。
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    int capacity = 1024*1024*100;
                    ByteBuffer buffer  = ByteBuffer.allocate(capacity);
                    for (int i = 0; i <capacity ; i++) {
                        buffer.put((byte)'a');
                    }
                    buffer.flip();
                    //这种情况，如果对方一直不接收，那么就会一直卡在这里发送了，导致整个服务器瘫痪..
                    if (buffer.hasRemaining()) {
                        //阻塞情况下
                        int len = sc.write(buffer);
                        if (len!=0) {
                            System.out.println("写出数据的长度-->"+len);
                        }
                    }
                }
            }
        }
    }


    public static void read(SelectionKey selectionKey){

        SocketChannel sc = (SocketChannel) selectionKey.channel();
        ByteBuffer buffer  = (ByteBuffer) selectionKey.attachment();
        try {
            int len = sc.read(buffer);
            System.out.println("读取数据"+len);
            System.out.println(buffer.position());
            if (len == -1) {//客户端调用close或者shutdownOutPutStream都会返回-1
                System.out.println("取消了sc");
                selectionKey.cancel();
            } else {
                spiltPack(buffer);
                if(buffer.position()==buffer.limit()){
                    ByteBuffer byteBuffer = increment(buffer);
                    selectionKey.attach(byteBuffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //客户端强制断开会触发一个read事件
            //sc.close();
            selectionKey.cancel();
        }
    }
}
