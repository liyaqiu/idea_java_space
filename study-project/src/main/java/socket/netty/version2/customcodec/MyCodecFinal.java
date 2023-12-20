package socket.netty.version2.customcodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

@Slf4j
@Sharable
public class MyCodecFinal extends MessageToMessageCodec<ByteBuf,MessageProtocol> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.debug("解码decode");
        //魔术 4字节
        byte[] magicNums = new byte[4];
        in.readBytes(magicNums);
        //版本 1字节
        byte version = in.readByte();
        //序列化方式 1字节
        byte serialType = in.readByte();
        //指令类型 1字节
        byte orderType = in.readByte();
        //序列号，预留4字节
        int serialNum = in.readInt();
        // 对齐填充字节 1字节
        in.readByte();
        //消息长度 4字节
        int contentLength = in.readInt();
        byte[] contenBytes = new byte[contentLength];
        //读取消息字节 如果不够消息长度会报错。
        in.readBytes(contenBytes);
        //反序列化对象
        if(MessageProtocol.JDK == version){
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(contenBytes));
            MessageProtocol messageProtocol = (MessageProtocol) ois.readObject();
            log.debug("魔术号:{},版本:{},序列化方式:{},指令类型:{},消息长度:{}",magicNums,version,serialType,orderType,contentLength);
            log.debug("消息{}",messageProtocol.getContent());
            //log.debug("多余的熊希{}",(char)in.readByte());
            out.add(messageProtocol);
        }

    }

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, List<Object> outList) throws Exception {
        log.debug("编码encode{}");
        ByteBuf out = ctx.alloc().buffer();
        //魔术号 4字节
        out.writeBytes(new byte[]{1,2,3,4});
        //版本 1字节
        out.writeByte(1);
        //序列化方式 1字节
        out.writeByte(MessageProtocol.JDK);
        //指令类型 1字节
        out.writeByte(MessageProtocol.LOGIN);
        //系列号，将来做异步请求，预留 4字节
        out.writeInt(0);
        //无意义填充对齐 2的整数倍 1字节
        out.writeByte(0xFF);
        //序列化对象
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(msg);
        byte[] contentBytes = baos.toByteArray();
        oos.close();
        baos.close();

        //消息长度 4歌字节
        out.writeInt(contentBytes.length);
        //一共16个字节
        // 消息
        out.writeBytes(contentBytes);
        outList.add(out);
    }

}
