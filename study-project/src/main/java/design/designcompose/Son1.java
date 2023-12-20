package design.designcompose;

import java.util.ArrayList;
import java.util.List;

public class Son1 extends Father{

    List<Father> son2 = new ArrayList<>();

    public Son1(String name) {
        super(name);
    }
    @Override
    protected void add(Father father) {
        son2.add(father);
    }

    @Override
    protected void remove(Father father) {
        son2.remove(father);
    }



    @Override
    protected void print() {
        System.out.println("son1"+getName());
        for (Father son2 : son2) {
            son2.print();
        }
    }
}
