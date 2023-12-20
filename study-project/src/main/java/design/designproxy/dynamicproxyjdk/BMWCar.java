package design.designproxy.dynamicproxyjdk;

public class BMWCar implements ICar {

    @Override
    public void run() {
        System.out.println("我在开车");
    }
    protected void run1() {
        System.out.println("我在开车");
    }
    @Override
    public final String run(String s) {
        System.out.println(s + "我在开车");
        test0();
        return "nihao";
    }


    public void test0(){
        System.out.println("test0");
    }


}
