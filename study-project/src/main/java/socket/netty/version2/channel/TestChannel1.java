package socket.netty.version2.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class TestChannel1 {


    public static void main(String[] args) {
        ChannelInboundHandlerAdapter in1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("channelRead:{}", 1);
                super.channelRead(ctx, msg);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                log.debug("channelReadComplete:{}", 1);
                super.channelReadComplete(ctx);
            }


        };
        ChannelInboundHandlerAdapter in2 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("channelRead:{}", 2);
                super.channelRead(ctx, msg);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                log.debug("channelReadComplete:{}", 2);
                super.channelReadComplete(ctx);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                log.debug("exceptionCaught:{}", 2);
                super.exceptionCaught(ctx, cause);
            }
        };
        ChannelOutboundHandlerAdapter out4 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 4);
                super.write(ctx, msg, promise);
            }

        };
        ChannelDuplexHandler in_out = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("channelRead:{}", 3);
                ctx.close();
                //ctx.write(msg);
            }
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 0);
                super.write(ctx, msg, promise);
            }
            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                log.debug("exceptionCaught {}",0);
                super.exceptionCaught(ctx, cause);
            }
        };
        ChannelOutboundHandlerAdapter out1 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 1);
                super.write(ctx, msg, promise);

            }
        };
        ChannelOutboundHandlerAdapter out2 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 2);
                super.write(ctx, msg, promise);
            }
        };
        ChannelOutboundHandlerAdapter out3 = new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 3);
                super.write(ctx, msg, promise);
                log.debug("write-end {}", 3);
            }

            @Override
            public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
                log.debug("close {}", 3);
                super.close(ctx, promise);
            }
        };


        EmbeddedChannel channel = new EmbeddedChannel(in1,in2,out4,in_out,out1,out2,out3);
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < 10000; i++) {
//            sb.append("我");
//        }
        ByteBuf buf = Unpooled.copiedBuffer("我们都是好孩子", Charset.defaultCharset());
        channel.writeInbound(buf);
        //channel.writeOutbound(buf);
    }
}
