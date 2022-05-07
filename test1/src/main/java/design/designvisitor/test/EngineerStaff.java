package design.designvisitor.test;

import java.util.Random;

// 工程师
public class EngineerStaff extends Staff {

    public EngineerStaff(String name) {
        super(name);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    // 工程师一年的代码数量
    public int getCodeLines() {
        return new Random().nextInt(10 * 10000);
    }
}