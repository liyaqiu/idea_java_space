package design.designdecorator;

public class Decorator extends Pack{

    protected Pack pack;

    public Decorator(Pack pack) {
        this.pack = pack;
    }

    @Override
    public void toPack() {
        pack.toPack();
    }
}
