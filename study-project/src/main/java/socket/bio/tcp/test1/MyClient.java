package socket.bio.tcp.test1;

import java.io.OutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",8888);
        System.out.println(socket.isConnected());
        Thread.sleep(100000);
        OutputStream os = socket.getOutputStream();
//        os.write("2我们都是好孩子".getBytes(StandardCharsets.UTF_8));
        System.out.println("已经连接上");
//        os.write("1111111111".getBytes(StandardCharsets.UTF_8));
//        System.out.println("已经发送");
//        new Scanner(System.in).next();
//        os.write("1111111111".getBytes(StandardCharsets.UTF_8));
//        while (true){
//           new Scanner(System.in).next();
//           StringBuffer sb  = new StringBuffer();
//           int count = 1024*1024*300;
//            for (int i = 0; i <count ; i++) {
//                sb.append('a');
//            }
//            sb.append('\n');;
//            os.write(sb.toString().getBytes(StandardCharsets.UTF_8));
//            System.out.println("22222222222222");
//        }



//
//        byte[] bytes = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        InputStream is = new BufferedInputStream(socket.getInputStream());
//        while ((len = is.read(bytes)) != -1) {
//            baos.write(bytes, 0, len);
//        }
//        socket.shutdownInput();
//        System.out.println(new String(baos.toByteArray(),"utf-8"));

//
//        os.close();
//        is.close();
//        socket.close();
//        System.out.println("客户端退出。。。");

    }
}


