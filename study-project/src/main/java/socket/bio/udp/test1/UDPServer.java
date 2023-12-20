package socket.bio.udp.test1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(6666);
        //socket.bind(new InetSocketAddress("0.0.0.0",6666));
        //接收最大数据位64k
        byte[] b = new byte[1024*64-29];
        DatagramPacket dp = new DatagramPacket(b,b.length);
        System.out.println("等待信息");
        socket.receive(dp);
        System.out.println("有信息");

        System.out.println(dp.getAddress().getHostAddress());
        System.out.println(dp.getAddress().getHostName());
        System.out.println(dp.getPort());
        System.out.println(dp.getLength());
        System.out.println(new String(b,0,100,"utf-8"));

        socket.send(new DatagramPacket(b, dp.getLength(), dp.getSocketAddress() ));


        socket.close();
    }
}
