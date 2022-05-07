package design.designproxy.staticproxy;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new BMW());
        List<String> run = proxy.run();
        System.out.println(run.toString());
    }
}
