package socket.netty.version2.channel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ServerChannelTest {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(10);
        new ServerBootstrap()
                .group(bossGroup, workGroup)//管理多个selector多路复用器
                .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                //.option(ChannelOption.SO_SNDBUF,10)
                //.childOption(ChannelOption.RCVBUF_ALLOCATOR,new AdaptiveRecvByteBufAllocator(16,16,16))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        ChannelPipeline pipeline = sc.pipeline();
                        Channel ch = pipeline.channel();
                        log.debug("有连接[hashcode: {}],channel:{}",ch.hashCode(),ch);
                        //sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        //sc.pipeline().addLast(new StringEncoder());
                        //sc.pipeline().addLast(new StringDecoder());
                        //ByteToMessageDecoder 各种解码器
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("channelRead:{}", 1);
                                super.channelRead(ctx, msg);//或者ctx.fireChannelRead(msg);
                            }
                        });
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("channelRead:{}", 2);
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)throws Exception  {
                                log.debug("write {}",4);
                                StringBuffer sb = new StringBuffer();
                                for (int i = 0; i < 10000000; i++) {
                                    sb.append("我");
                                }
                                ByteBuf byteBuf = ctx.alloc().buffer().writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));
                                System.out.println(byteBuf.getClass());
                                ctx.write(byteBuf);
                                log.debug("已经发送:write {}",4);
                            }
                        });
                        pipeline.addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("channelRead:{}", 3);
                                ByteBuf buf = (ByteBuf) msg;
                                if ("1".equals(buf.toString(Charset.defaultCharset()))) {
                                    ch.write(msg);
                                }else{
                                    ctx.write(msg);
                                }
                            }
                        });
                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("write {}",1);
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("write {}",2);
                                super.write(ctx, msg, promise);
                            }
                        });
                        pipeline.addLast(new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("write {}",3);
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                }).bind(9999).sync();
        log.debug("服务器启动成功。。。");
    }
}
