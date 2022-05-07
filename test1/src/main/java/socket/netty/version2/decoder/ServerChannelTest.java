package socket.netty.version2.decoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ServerChannelTest {
    static volatile boolean bl = true;
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(2);
        new ServerBootstrap()
                .group(bossGroup, workGroup)//管理多个selector多路复用器
                .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                .childOption(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(16,16,16))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ChannelPipeline pipeline = sc.pipeline();
                        Channel ch = pipeline.channel();
                        log.debug("有连接[hashcode: {}],channel:{}",ch.hashCode(),ch);
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(4096, 0, 4, 0, 4));
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                log.debug("收到客户端的数据:{}", byteBuf.toString(Charset.defaultCharset()));
                                EventLoop eventLoop = ctx.pipeline().channel().eventLoop();
                                if(bl){
                                    eventLoop.schedule(new Runnable() {
                                        @SneakyThrows
                                        @Override
                                        public void run() {
                                            log.debug("3执行结果");
                                            Thread.sleep(100000000);
                                            log.debug("3处理完毕");
                                        }
                                    }, 0, TimeUnit.SECONDS);
                                    eventLoop.execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            log.debug("1执行结果");
                                        }
                                    });
                                    eventLoop.submit(new Callable<String>() {
                                        @Override
                                        public String call() throws Exception {
                                            log.debug("2执行结果");
                                            return "hello";
                                        }
                                    });
                                    log.debug("提交完成");
                                    bl =false;
                                }
                            }
                        });
                    }
                }).bind(7777).sync();
        log.debug("服务器启动成功。。。");
    }
}
