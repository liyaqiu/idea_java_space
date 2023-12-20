package socket.netty.version2.customcodec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class ClientChannelTest {
    public static void main(String[] args) throws InterruptedException {

        NioEventLoopGroup executors = new NioEventLoopGroup(1);
        ChannelFuture future = new Bootstrap()
                .group(executors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        sc.pipeline().addLast(new MyCodec());
                    }
                }).connect("localhost", 6666);

        future.sync();
        Channel channel = future.channel();
        log.debug("当前的channel:{}"+channel);

        while (true){
            String s = new Scanner(System.in).next();
            MessageProtocol messageProtocol = new MessageProtocol(s);
            System.out.println(channel.isActive());
            channel.writeAndFlush(messageProtocol);
            log.debug("信息已经发送");//16  19    19
        }

    }

}
