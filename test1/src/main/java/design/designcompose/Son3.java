package design.designcompose;

public class Son3 extends Father{

    public Son3(String name) {
        super(name);
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }
}
