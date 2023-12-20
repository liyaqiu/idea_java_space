package socket.bio.udp.test1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9999, InetAddress.getByName("0.0.0.0"));

        byte[] bytes = new byte[1024*64-29];
        for (int i = 0; i <bytes.length ; i++) {
            bytes[i] = 'a';
        }

        socket.send(new DatagramPacket(bytes,bytes.length, new InetSocketAddress("127.0.0.1", 6666)));

        byte[] bytes1 = new byte[1024*64-29];
        DatagramPacket dp = new DatagramPacket(bytes1, bytes.length);
        socket.receive(dp);
        System.out.println(dp.getAddress().getHostAddress());
        System.out.println(dp.getAddress().getHostName());
        System.out.println(dp.getPort());
        System.out.println(dp.getLength());

        socket.close();
    }
}
