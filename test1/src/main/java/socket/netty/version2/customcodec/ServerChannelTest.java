package socket.netty.version2.customcodec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class ServerChannelTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(10);
        int len = 50;
        new ServerBootstrap()
                .group(bossGroup, workGroup)//管理多个selector多路复用器
                .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                //.option(ChannelOption.SO_SNDBUF,10)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(len,len,len))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ChannelPipeline pipeline = sc.pipeline();
                        Channel ch = pipeline.channel();
                        log.debug("有连接[hashcode: {}],channel:{}",ch.hashCode(),ch);
                        pipeline.addLast(new LoggingHandler1(LogLevel.DEBUG));
                        pipeline.addLast(new LengthFieldBasedFrameDecoder(19, 12, 4, 0, 0));
                        pipeline.addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                log.debug("异常...");
                                ctx.close();
                                //super.exceptionCaught(ctx, cause);
                            }
                        });
                        pipeline.addLast(new LoggingHandler2(LogLevel.DEBUG));
                        pipeline.addLast(new MyCodec());
                        pipeline.addLast(new SimpleChannelInboundHandler<MessageProtocol>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
                                log.debug("获取到的消息:{}", msg.getContent());
                            }
                        });
                    }
                }).bind(6666).sync();
        log.debug("服务器启动成功。。。");
    }
}
