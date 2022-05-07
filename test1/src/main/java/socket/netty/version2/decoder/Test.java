package socket.netty.version2.decoder;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        while (true){
            switch ("1"){
                case "1" :
                    System.out.println(1);
                    continue;
                case "2" :
                    System.out.println(2);
                default:
                    System.out.println("default");
            }
            Thread.sleep(2000);
        }

    }
}
