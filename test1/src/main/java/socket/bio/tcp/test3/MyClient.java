package socket.bio.tcp.test3;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1",9999);

//        ExecutorService service = Executors.newFixedThreadPool(3);
//        service.execute(new Runnable() {
//            @Override
//            public void run() {
//                ObjectInputStream ois = null;
//                try {
//                    ois = new ObjectInputStream(socket.getInputStream());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                while (true){
//                    try {
//                        System.out.println(Thread.currentThread()+"  "+ois.readObject());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        while (true){
            String next = new Scanner(System.in).next();
            if(next.equals("33")){
                socket.close();
                break;
            }

            oos.writeObject(new Person(Thread.currentThread().getName(),12));
        }



//
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


        //socket.shutdownOutput();

//        byte[] bytes = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        InputStream is = new BufferedInputStream(socket.getInputStream());
//        while ((len = is.read(bytes)) != -1) {
//            baos.write(bytes, 0, len);
//        }
//        socket.shutdownInput();
//        System.out.println(new String(baos.toByteArray(),"utf-8"));


//        os.close();
//        is.close();
        Thread.sleep(100000);
//        socket.close();
//        System.out.println("客户端退出。。。");

    }
}


