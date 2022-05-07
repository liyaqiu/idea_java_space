package socket.netty.version2.channel;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
                        //sc.pipeline().addLast(new StringEncoder());
                        //sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug("客户端回送消息:{}",buf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                }).connect("localhost", 9999);

        future.sync();
        Channel channel = future.channel();
        log.debug("当前的channel:{}"+channel);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10000; i++) {
            sb.append("我");
        }

        ByteBuf buf = Unpooled.copiedBuffer(sb.toString(), Charset.defaultCharset());
        log.debug("可读字节数:{}",buf.readableBytes());
        log.debug("信息长度:{}",buf.capacity());
        while (true){
            String s = new Scanner(System.in).next();
            ByteBuf byteBuf = channel.alloc().buffer().writeBytes(s.getBytes(StandardCharsets.UTF_8));
            channel.writeAndFlush(byteBuf);
            log.debug("信息已经发送");
        }

    }
}
