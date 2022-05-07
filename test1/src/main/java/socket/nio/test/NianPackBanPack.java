package socket.nio.test;




import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 因为数据链路层的原因，导致的黏包和半包的数据处理
 * */
public class NianPackBanPack {
    public static void main(String[] args) throws UnsupportedEncodingException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("我们都说我是123\n好孩子456\n但是".getBytes(StandardCharsets.UTF_8));
        spiltPack(buffer);
        buffer.put("我不同意789\n".getBytes(StandardCharsets.UTF_8));
        spiltPack(buffer);
    }

    public static void spiltPack(ByteBuffer buffer) {
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            if(buffer.get(i)=='\n'){
                //16 i=22  p=0
                //10 i=25  p=16 10
                //10   25  15
                //System.out.println(i);
                int len = i+1- buffer.position();
                ByteBuffer buf = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    buf.put(buffer.get());
                }
                System.out.print(StandardCharsets.UTF_8.decode(buf).toString());
            }
        }
        buffer.compact();
    }

}
