package socket.netty.version2.decoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class ClientChannelTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup executors = new NioEventLoopGroup(10);
        EventLoop eventLoop = executors.next();
        ChannelFuture future = new Bootstrap()
                .group(executors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        //sc.pipeline().addLast(new StringEncoder());
                        //sc.pipeline().addLast(new StringDecoder());
                    }
                }).connect("localhost", 7777);

        future.sync();
        Channel channel = future.channel();
        log.debug("当前的channel:{}"+channel);

        while (true){
            String s = new Scanner(System.in).next();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i <Integer.valueOf(s) ; i++) {
                sb.append('a');
            }
            channel.writeAndFlush(messageWarp(sb.toString()));
            log.debug("信息已经发送");
        }

    }



    public static ByteBuf messageWarp(String mesg){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byte[] bytes = mesg.getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes,0,bytes.length);
        return byteBuf;
    }
}
