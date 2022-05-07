package design.designproxy.dynamicproxycglib;

//不能为final类
public class BMW {

    public static final void run1() {
        System.out.println("静态或者final类都不会被拦截到");
    }

    public  String run(String name){
        System.out.println(name + "在奔跑");
        run1("name");
        return name;
    }
    private  String run1(String name){
        System.out.println(name + "private在奔跑");
        return name;
    }
}
