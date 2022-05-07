package socket.netty.version2.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.NetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ServerChannelTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());//默认是核心数*2
        int size = 300;
        new ServerBootstrap()
                .group(bossGroup, workGroup)//管理多个selector多路复用器
                .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                .childOption(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(size,size,size))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ChannelPipeline pipeline = sc.pipeline();
                        Channel ch = pipeline.channel();
                        log.debug("有连接[hashcode: {}],channel:{}",ch.hashCode(),ch);
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        sc.pipeline().addLast(new HttpServerCodec());
                        sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        //ByteToMessageDecoder 各种解码器
                        pipeline.addLast(new SimpleChannelInboundHandler<HttpRequest>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, HttpRequest request) throws Exception {

                                log.debug("msg:{}",request.getClass());
                                log.debug("url:{}",request.uri());
                                ctx.channel().close();
//                                DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK);
//                                byte[] content = "<h1>hello,world</h1>".getBytes(StandardCharsets.UTF_8);
//                                response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH,content.length);
//                                response.content().writeBytes(content);
//                                ctx.writeAndFlush(response);
                            }
                        });
                    }
                }).bind(9999).sync();
        log.debug("服务器启动成功。。。");
    }
}
