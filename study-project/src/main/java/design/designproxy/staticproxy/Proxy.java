package design.designproxy.staticproxy;

import java.util.ArrayList;
import java.util.List;

public class Proxy implements ICar{

    private ICar iCar;

    public Proxy(ICar iCar) {
        this.iCar = iCar;
    }

    @Override
    public List<String> run() {
        System.out.println("我要让你飞");
        iCar.run();
        System.out.println("我要让你降落");
        List<String> list = new ArrayList<>();
        list.add("111");
        return list;
    }
}
