package design.designdecorator;

public class Rule extends Decorator{
    public Rule(Pack pack) {
        super(pack);
    }

    @Override
    public void toPack() {
        super.toPack();
        System.out.println("将尺子装入书包");
    }
}
