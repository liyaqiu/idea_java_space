package socket.netty.version2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import socket.netty.version2.customcodec.LoggingHandler1;

import java.util.Scanner;

@Slf4j
public class ServerTest {

    public static void main(String[] args)  {
        Thread.currentThread().setName("zhuxiancheng");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup = new NioEventLoopGroup(2);
        EventLoopGroup group = new DefaultEventLoopGroup(1);

            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)//管理多个selector多路复用器
                    .channel(NioServerSocketChannel.class)//什么类型的io：nio bio
                    .handler(new LoggingHandler1(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            log.debug("有客服端链接channle:{},hashcode:{}", sc, sc.hashCode());
                            sc.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                            sc.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = (ByteBuf) msg;
                                    log.debug("信息长度:{}", buf.capacity());
                                    log.debug("可读字节数:{}", buf.readableBytes());
                                    log.debug("当前的channle:{},hashcode:{}", ctx.channel(), ctx.channel().hashCode());
                                    log.debug("发过来的信息[{}]", ctx.channel(), buf.toString(CharsetUtil.UTF_8));
                                    ChannelFuture future = ctx.writeAndFlush(buf);
                                            future.sync();
//                                    future.addListener(new ChannelFutureListener() {
//                                        @Override
//                                        public void operationComplete(ChannelFuture future) throws Exception {
//                                                 log.debug("是否成功{}",future.isSuccess());
//                                        }
//                                    });
                                    log.debug("是否成功{}",future.isSuccess());
                                }


                            });
//                            sc.pipeline().addLast(group,new ChannelInboundHandlerAdapter() {
//                                @Override
//                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                                    System.out.println("thread22---->"+Thread.currentThread().getName());
//                                    System.out.println("channel22-->"+ctx.channel().hashCode());
//                                    //Thread.sleep(30*1000);
//                                    ByteBuf buf = (ByteBuf) msg;
//                                    ctx.fireChannelRead(msg);
//                                    System.out.println(buf.toString(CharsetUtil.UTF_8)+"----22派发完成........");
//                                }
//
//                            });

                        }
                    });
        try {
            ChannelFuture future = null;
            while (true){
                future = serverBootstrap.bind(7777).await();
                if(future.isSuccess()){
                    break;
                }
            }
            Channel channel = future.channel();
            log.debug("绑定成功后需要做什么事情......{}...{}", future.channel(), future.channel().hashCode());
            final ChannelFuture future1 = future;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Scanner(System.in).next();
                    log.debug("服务器被攻击关闭");
                    future1.channel().close();
                }
            }, "其他web应用").start();
            future.channel().closeFuture().addListener((ChannelFutureListener) channelFuture -> {
                log.debug("关闭线程组");
                workGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
                group.shutdownGracefully();
                log.debug("程序退出....");
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("服务器启动完毕");
    }
}
