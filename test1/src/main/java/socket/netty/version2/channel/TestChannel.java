package socket.netty.version2.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class TestChannel {


    public static void main(String[] args) {
        ChannelInboundHandlerAdapter in1 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("channelRead:{}", 1);
                super.channelRead(ctx, msg);

            }
        };
        ChannelInboundHandlerAdapter in2 = new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.debug("channelRead:{}", 2);
                super.channelRead(ctx, msg);


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
                ctx.channel().write(msg);
                //ctx.write(msg);

            }
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                log.debug("write {}", 0);
                super.write(ctx, msg, promise);

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
        boolean b = channel.writeInbound("");
    }
}
