package socket.bio.tcp.test3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyServer {
    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        System.out.println("有连接过来");
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new Runnable() {
            @Override
            public void run() {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                while (true){
                    try {
                        System.out.println("等待不接收");
                        String next = new Scanner(System.in).next();
                        if(next.equals("33")){
                            socket.close();
                            break;
                        }else{
                            System.out.println(Thread.currentThread()+"  "+ois.readObject());
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



//        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
//        boolean bl = true;
//        int i =0;
//        while (bl){
//            i++;
//
//            String next = new Scanner(System.in).next();
//
//            if (next.equals("1fff")) {
//                System.out.println("停止输出");
//                bl=false;
//                socket.shutdownOutput();
//                ObjectOutputStream oos1 = new ObjectOutputStream(socket.getOutputStream());
//                oos1.writeObject(new Person("李雅秋"+i,12));
//            }else{
//                oos.writeObject(new Person("李雅秋"+i,12));
//            }
//
//        }
//
//
//        is.close();
//        os.close();
        Thread.sleep(10000000);
//        socket.close();
//        server.close();
//        System.out.println("服务器退出");
    }
}
