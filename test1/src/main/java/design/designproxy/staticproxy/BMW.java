package design.designproxy.staticproxy;

import java.util.ArrayList;
import java.util.List;

public final class BMW implements ICar{
    @Override
    public List<String> run() {
        System.out.println("我在路上行走的宝马");
        return new ArrayList<>();
    }
}
