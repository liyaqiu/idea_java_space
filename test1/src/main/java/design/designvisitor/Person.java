package design.designvisitor;

public abstract class  Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    protected String getName(){
        return this.name;
    }

   public abstract void accept(Action action);
}
