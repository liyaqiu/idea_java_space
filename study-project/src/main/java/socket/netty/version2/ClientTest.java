package socket.netty.version2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class ClientTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        NioEventLoopGroup executors = new NioEventLoopGroup(1);
        ChannelFuture future = new Bootstrap()
                .group(executors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        sc.pipeline().addLast(new StringEncoder());
                    }
                }).connect("localhost", 7777);

        future.sync();
        Channel channel = future.channel();
        while (true){
           new Scanner( System.in).next();
            channel.writeAndFlush("nihao");
            System.out.println("已经发送....");
        }


    }
}
