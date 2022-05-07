package socket.netty.version2.customcodec;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class MessageProtocol implements Serializable {
    private String content;

    //序列化类型
    public static final byte JDK = 0b1;
    public static final byte JSON = 0b10;

    //指令类型
    public static final byte LOGIN = 0b1;
    public static final byte REGISTER = 0b10;
}
