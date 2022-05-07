package thread.test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    private  static int a =100;
    static {
        System.out.println("!111");
    }
    static  String string ;

    public static void a() throws InterruptedException{
        b();
    }
    public static void b() throws InterruptedException {
        c();
    }
    public static void c() throws InterruptedException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        List<byte[]> list = new ArrayList<>();


        //Thread.sleep(Integer.MAX_VALUE);
    }
    public static void main(String[] args) throws  Exception{
     //  a();
    a--;
        System.out.println("1");
    }
    static synchronized void haha(){
        System.out.println("哈哈");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test{
    private  static int num =100;
    public  void haha(){
        num--;

    }
}

class Animal implements Comparable<Animal>{
     byte[] bytes = new byte[1024*1024*10];
//    @Override
//    public int hashCode() {
//        System.out.println("hashcode");
//        return super.hashCode();
//    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("equals");
        return super.equals(obj);
    }

    @Override
    public int compareTo(Animal o) {
        System.out.println("2222");
        return 0;
    }
}



