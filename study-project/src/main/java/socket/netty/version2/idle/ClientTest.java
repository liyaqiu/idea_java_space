package socket.netty.version2.idle;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

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
                        sc.pipeline().addLast(new IdleStateHandler(0, 3, 0 ));
                        sc.pipeline().addLast(new ChannelDuplexHandler() {
                            @Override
                            public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                if (evt instanceof IdleStateEvent) {
                                    IdleStateEvent e = (IdleStateEvent) evt;
                                    if (e.state() == IdleState.WRITER_IDLE) {
                                        log.debug("写空闲3秒");
                                        ctx.writeAndFlush("ping..");
                                    }
                                }
                            }
                        });

                    }
                }).connect("localhost", 7777);

        future.sync();
        Channel channel = future.channel();
        log.debug("当前的channel:{}"+channel);
        channel.closeFuture().sync();
        System.err.println("断开连接");
        executors.shutdownGracefully();
    }
}
