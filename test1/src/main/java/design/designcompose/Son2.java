package design.designcompose;

import java.util.ArrayList;
import java.util.List;

public class Son2 extends Father{
    List<Father> son3 = new ArrayList<>();

    public Son2(String name) {
        super(name);
    }



    @Override
    protected void add(Father father) {
        son3.add(father);
    }

    @Override
    protected void remove(Father father) {
        son3.remove(father);
    }



    @Override
    protected void print() {
        System.out.println("son2"+getName());
        for (Father son3 : son3) {
            son3.print();
        }
    }
}
