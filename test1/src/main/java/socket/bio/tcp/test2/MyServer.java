package socket.bio.tcp.test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class MyServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        InputStream inputStream = socket.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        //System.out.println(br.readLine());
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println("数据："+line);
            if(line.equals("")){
                break;
            }
        }
        System.out.println("执行真实数据处理");
        byte[] b = new byte[5];
        int i = inputStream.read(b);
        System.out.println(i);
        System.out.println("真实数据:"+new String(b,0,i));

        System.out.println("服务器端接收完毕");

//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
//        bw.write("客户端你好");
//        bw.newLine();
//        bw.flush();
//        bw.write("客户端你好");
//        bw.newLine();
//        bw.flush();
//        socket.shutdownOutput();
//        System.out.println("服务器发送完毕");




        br.close();
       // bw.close();
        socket.close();
        server.close();
        System.out.println("服务器退出");
    }
}
