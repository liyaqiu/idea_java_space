package design.designproxy.dynamicproxycglib;

public class Test {
    public static void main(String[] args) {
        ProxyFactory<BMW> proxyFactory = new ProxyFactory<>(new BMW());
        BMW bmw = proxyFactory.getProxy();


        System.out.println(bmw.run("超人"));
        /*System.out.println(bmw);
        System.out.println(bmw.getClass());*/
        //bmw.run();
    }
}
