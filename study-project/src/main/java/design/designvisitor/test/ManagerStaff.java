package design.designvisitor.test;

import java.util.Random;

// 经理
public class ManagerStaff extends Staff {

    public ManagerStaff(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    // 一年做的产品数量
    public int getProducts() {
        return new Random().nextInt(10);
    }
}