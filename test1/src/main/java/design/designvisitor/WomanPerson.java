package design.designvisitor;

public class WomanPerson extends Person{

    public WomanPerson(String name) {
        super(name);
    }

    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
