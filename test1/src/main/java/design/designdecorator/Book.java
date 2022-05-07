package design.designdecorator;

public class Book extends Decorator{


    public Book(Pack pack) {
        super(pack);
    }

    @Override
    public void toPack() {
        super.toPack();

        System.out.println("将课本装入书包");
    }
}
