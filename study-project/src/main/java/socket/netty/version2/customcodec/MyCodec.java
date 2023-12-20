package socket.netty.version2.customcodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MyCodec extends ByteToMessageCodec<MessageProtocol> {
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
        log.debug("魔术号:{},版本:{},序列化方式:{},指令类型:{},消息长度:{}",magicNums,version,serialType,orderType,contentLength);
        log.debug("消息{}",new String(contenBytes, 0, contentLength));
    }
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        if(msg.getContent().equals("1")){
            log.debug("发消息头");
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
            //一共16个字节
            // 消息
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            //消息长度 4歌字节
            out.writeInt(bytes.length+1);


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
            //一共16个字节
            // 消息
            byte[] bytes1 = "abc".getBytes(StandardCharsets.UTF_8);
            //消息长度 4歌字节
            out.writeInt(bytes1.length);

        }else if(msg.getContent().equals("2")){
            log.debug("发消息体+消息头{}");
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            out.writeBytes(bytes);
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
            //一共16个字节
            // 消息
            //消息长度 4歌字节
            out.writeInt(bytes.length);

        }else if(msg.getContent().equals("3")){
            log.debug("发消息体{}");
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            out.writeBytes(bytes);

        }else if(msg.getContent().equals("4")){
            log.debug("整条消息{}");
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
            //一共16个字节
            // 消息
            byte[] bytes = "abc".getBytes(StandardCharsets.UTF_8);
            //消息长度 4歌字节
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }else if(msg.getContent().equals("5")){
            log.debug("整条消息{}");
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
            //一共16个字节
            // 消息
            byte[] bytes = "ab".getBytes(StandardCharsets.UTF_8);
            //消息长度 4歌字节
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }


    }


}
