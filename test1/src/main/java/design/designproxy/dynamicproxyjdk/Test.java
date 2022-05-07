package design.designproxy.dynamicproxyjdk;

public class Test {

    public static void main(String[] args) {


        ICar iCar = (ICar) new ProxyFactory(new BMWCar()).getProxy();
        iCar.run("李雅秋");


        //System.out.println(iCar);
        //System.out.println(iCar.getClass());
//
//        System.out.println(new AA());
//        System.out.println(new AA().getClass());
//        new BMWCar().run1();

        /*ICar proxy = (ICar) ProxyFactory.getRPCProxy(ICar.class);
        proxy.run("你好");*/

    }
    public static class AA{

    }
}
