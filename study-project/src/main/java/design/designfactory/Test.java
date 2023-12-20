package design.designfactory;

public class Test {
    public static void main(String[] args) {
        ConfigFactory factory = new MysqlFactory();
        System.out.println(factory.getWindowConfig());
    }
}
