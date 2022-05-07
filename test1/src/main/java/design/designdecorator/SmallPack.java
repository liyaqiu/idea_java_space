package design.designdecorator;

public class SmallPack extends Pack{
    @Override
    public void toPack() {
        System.out.println("打开小书包");
    }
}
