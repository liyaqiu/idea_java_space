package socket.bio.tcp.test1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(8888), 2);

        Socket socket = server.accept();
        InputStream is = socket.getInputStream();

        //new Scanner(System.in).next();

        byte[] bytes = new byte[1];
        is.read(bytes);
        if(!new String(bytes,"utf-8").equals("1")){
            System.out.println("协议不对，退出...");
            System.exit(0);
        }
        System.out.println("协议对上。。可以继续往下执行...");
        bytes = new byte[21];
        is.read(bytes);
        System.out.println(new String(bytes));

//        int len = 0;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        while (true) {
//            len = is.read(bytes);
//            if(len<10){
//                if(len!=-1){
//                    baos.write(bytes,0,len);
//                    break;
//                }
//                break;
//            }
//            //System.out.println(len);
//           baos.write(bytes);
//        }
//        //socket.shutdownInput();
//        System.out.println(new String(baos.toByteArray(),"utf-8"));

        Thread.sleep(1000000000);

        OutputStream os = socket.getOutputStream();
        os.write("客户端你好".getBytes(StandardCharsets.UTF_8));
        os.write("客户端你好".getBytes(StandardCharsets.UTF_8));
        os.write("客户端你好".getBytes(StandardCharsets.UTF_8));
        socket.shutdownOutput();


        is.close();
        os.close();
        socket.close();
        server.close();
        System.out.println("服务器退出");
    }
}
