package socket.netty.version2.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 直接内存 读写快，分配慢  默认就用直接内存
 * 堆内存  读写慢，分配快
 * 池化直接内存 读写快 分配快
 * 池化堆堆内存 读写慢 分配快
 * */
@Slf4j
public class NettyBuf {
    @Test
    public void test07(){
        //netty 的 bytebuf会自动扩容, 默认获取池化直接内存PooledUnsafeDirectByteBuf
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        log.debug("最大容量:{}",buffer.maxCapacity());
        log.debug("capacity:{}:{}",buffer.capacity(),buffer.getClass());
        buffer.writeBytes("我们都是好孩子".getBytes(StandardCharsets.UTF_8));
        log.debug("结果:{}",buffer.toString(Charset.defaultCharset()));
        log.debug("capacity:{}",buffer.capacity());
        buffer.retain();
        buffer.retain();
        buffer.retain();
        while (!buffer.release()){
            System.out.println("释放1次");
        }

        //log.debug("结果:{}",buffer.getByte(0));
    }
    @Test
    public void test02(){
        //netty 的 bytebuf会自动扩容, 默认获取池化直接内存PooledUnsafeDirectByteBuf
        //ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        //ByteBuf buffer = ByteBufAllocator.DEFAULT.directBuffer(10);
        ByteBuf buffer = ByteBufAllocator.DEFAULT.heapBuffer(1,10000);
        log.debug("最大容量:{}",buffer.maxCapacity());
        log.debug("capacity:{}:{}",buffer.capacity(),buffer.getClass());
        //buffer.writeBytes("我们都是好孩子".getBytes(StandardCharsets.UTF_8));
        buffer.writeCharSequence("我们都是好孩子", Charset.defaultCharset());
        log.debug("结果:{}",buffer.toString(Charset.defaultCharset()));
        log.debug("capacity:{}",buffer.capacity());
    }

    @Test //合并（映射指针方式）实现零拷贝
    public void test06(){
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(new byte[]{'a','b','c','d'});
        buffer.retain();

        ByteBuf buffer1 = Unpooled.buffer();
        buffer1.writeBytes(new byte[]{'e','f','g','h'});
        buffer1.retain();

        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        compositeByteBuf.addComponents(true, buffer,buffer1);
        compositeByteBuf.release();


        log.debug("buffer:{}",buffer.toString(Charset.defaultCharset()));
        log.debug("buffer1:{}",buffer1.toString(Charset.defaultCharset()));
        log.debug("compositeByteBuf:{}",compositeByteBuf.toString(Charset.defaultCharset()));
    }
    @Test //利用切片（映射指针方式）实现零拷贝
    public void test05(){
        ByteBuf buffer = Unpooled.directBuffer();
        buffer.writeBytes(new byte[]{'a','b','c','d'});

        //buffer.duplicate()
        ByteBuf slice1 = buffer.slice(0, 2);
        ByteBuf slice2 = buffer.slice();
        slice1.setByte(0, 'p');

        System.out.println(buffer.hashCode());
        System.out.println(slice1.hashCode());
        System.out.println(slice2.hashCode());

        log.debug("buffer:{}",buffer.toString(Charset.defaultCharset()));
        log.debug("slice1:可读字节：{},最大容量：{},{}",slice1.readableBytes(),slice1.maxCapacity(),slice1.toString(Charset.defaultCharset()));
        log.debug("slice2:可读字节：{},最大容量：{},{}",slice2.readableBytes(),slice1.maxCapacity(),slice2.toString(Charset.defaultCharset()));
        slice1.retain();
        slice2.retain();
        buffer.release();
        slice2.release();
        log.debug("buffer:{}",buffer.toString(Charset.defaultCharset()));
        log.debug("slice1:可读字节：{},最大容量：{},{}",slice1.readableBytes(),slice1.maxCapacity(),slice1.toString(Charset.defaultCharset()));
    }
    @Test
    public void test04(){
        //netty 的 bytebuf会自动扩容
        ByteBuf buffer = Unpooled.buffer(10);
        log.debug("capacity:{}",buffer.capacity());
        buffer.writeBytes("我们都是好孩子".getBytes(StandardCharsets.UTF_8));
        log.debug("capacity:{}",buffer.capacity());
    }

    @Test
    public void test03(){
        //netty 的 bytebuf会自动扩容，默认非池化堆内存UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf
        ByteBuf buffer = Unpooled.buffer(10);
        //ByteBuf buffer = Unpooled.directBuffer(1);
        log.debug("capacity:{}:{}",buffer.capacity(),buffer.getClass());
        buffer.writeBytes("我们都是好孩子".getBytes(StandardCharsets.UTF_8));
        log.debug("capacity:{}",buffer.capacity());
    }

    @Test
    public void test01(){
        ByteBuf buffer = Unpooled.buffer(100);
        System.out.println(0xFF);
        buffer.writeByte(0xFF);
        System.out.println(buffer.readByte());
        System.out.println(Integer.toBinaryString(255));
//        byte[] bytes = new byte[]{'a','b','c','d','e','f'};
//        buffer.writeBytes(bytes);
//        //读取必须大于或者等于需要的长度
//        byte[] bytes1 = new byte[60];
//        buffer.readBytes(bytes1);
//
//        System.out.println(Arrays.toString(bytes1));
//        System.out.println(buffer.isReadable());
    }

}
