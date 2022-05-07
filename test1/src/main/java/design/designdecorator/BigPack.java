package design.designdecorator;

public class BigPack extends Pack {
    @Override
    public void toPack() {
        System.out.println("打开大书包");
    }
}
