package socket.netty.version1;

import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class Test1 {
    public static void main(String[] args) {
        try {
            heihei();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-------------------");
//            System.out.println(e.getMessage());
//            System.out.println(e.getCause().getMessage());

        }
    }
    static void heihei(){

        try {
            System.out.println(1/0);
        } catch (Exception e) {
            e.initCause(e);
            throw e;
            //e.printStackTrace();
            //throw new RuntimeException(e.getMessage());
        }
    }
}

interface Inter{
    void exec();
}

class Father1{
    String name;
    public Father1(String name){
        this.name = name;
    }
    public void exec(){};


}

class Son extends  Father1 implements Inter{

    public Son(String name) {
        super(name);
    }
}
class Son1 extends  Father1 implements Inter{
    public Son1(String name) {
        super(name);
    }

    public void exec(){};
}

