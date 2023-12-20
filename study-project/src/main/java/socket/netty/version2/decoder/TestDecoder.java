package socket.netty.version2.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class TestDecoder {
    public static void main(String[] args) {
        LengthFieldBasedFrameDecoder protocolDecoder = new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4);
        EmbeddedChannel channel = new EmbeddedChannel(new LoggingHandler(LogLevel.DEBUG),protocolDecoder,new LoggingHandler(LogLevel.DEBUG));


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <1000 ; i++) {
            sb.append('a');
        }

        channel.writeInbound(messageWarp(sb.toString()));
        //channel.writeInbound(messageWarp("hi!"));

    }


    public static ByteBuf messageWarp(String mesg){
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        byte[] bytes = mesg.getBytes(StandardCharsets.UTF_8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

}
