package socket.netty.version2.customcodec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.internal.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Queue;

@Slf4j
public class CustomCodecHandler {
    public static void main(String[] args) {
        MyCodec myCodec = new MyCodec();
        LoggingHandler1 loggingHandler1 = new LoggingHandler1(LogLevel.DEBUG);
        LoggingHandler2 loggingHandler2 = new LoggingHandler2(LogLevel.DEBUG);

        EmbeddedChannel channel = new EmbeddedChannel(loggingHandler1,myCodec,loggingHandler2);

        //收到 MessageProtocol 进行编码，在经过父级的handler在进行bytebuf发送出去
        channel.writeOutbound(new MessageProtocol("我是李雅秋"));
        Queue<Object> queue = channel.outboundMessages();
        ByteBuf mesgBuf = (ByteBuf) queue.poll();
        log.debug("{}",mesgBuf.toString());

        //入口是要以butebuf进来，在经过解码器
        channel.writeInbound(mesgBuf);
    }
}



