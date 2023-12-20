package socket.netty.version2.idle;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(1);


        ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .option(ChannelOption.SO_BACKLOG,1)
                    .group(bossGroup, workGroup)//管理多个selector多路复用器
                    .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            log.debug("有客服端链接channle:{},hashcode:{}", sc, sc.hashCode());
                            sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            sc.pipeline().addLast(new IdleStateHandler(5, 0, 0 ));
                            sc.pipeline().addLast(new ChannelDuplexHandler() {
                                @Override
                                public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                    if (evt instanceof IdleStateEvent) {
                                        IdleStateEvent e = (IdleStateEvent) evt;
                                        if (e.state() == IdleState.READER_IDLE) {
                                            log.debug("读空闲5秒,断开连接");
                                            ctx.close();
                                        }
                                    }
                                }
                            });
                        }
                    });
        serverBootstrap.bind(6666).sync();
        log.debug("服务器启动完毕");
    }
}
