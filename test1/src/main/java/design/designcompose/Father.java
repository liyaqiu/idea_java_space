package design.designcompose;

public abstract class Father {

    private String name;

    public String getName() {
        return name;
    }

    public Father(String name) {
        this.name = name;
    }

    protected void add(Father father){
        throw new UnsupportedOperationException();
    }
    protected void remove(Father father){
        throw new UnsupportedOperationException();
    }
    protected abstract void print();
}
