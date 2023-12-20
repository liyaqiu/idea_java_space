package design.designvisitor;

public class ManPerson extends Person{

    public ManPerson(String name) {
        super(name);
    }

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
