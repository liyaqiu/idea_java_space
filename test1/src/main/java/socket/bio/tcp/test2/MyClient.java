package socket.bio.tcp.test2;

import java.io.*;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",9999);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf8"));
        bw.write("服务器你好");
        //发送换行符代表对面收到换行就可以输出，但不代表流结束
        bw.newLine();
        bw.flush();
        socket.shutdownOutput();



        String line = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        bw.close();
        br.close();
        socket.close();
        System.out.println("客户端退出。。。");
    }
}
