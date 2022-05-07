package design.designvisitor.test;

public class CTOVisitor implements Visitor {
    @Override
    public void visit(EngineerStaff engineer) {
        System.out.println("工程师: " + engineer.name + ", 代码行数: " + engineer.getCodeLines());
    }

    @Override
    public void visit(ManagerStaff manager) {
        System.out.println("经理: " + manager.name + ", 产品数量: " + manager.getProducts());
    }
}