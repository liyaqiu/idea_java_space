package design.designvisitor.test;

// CEO访问者
public class CEOVisitor implements Visitor {
    @Override
    public void visit(EngineerStaff engineer) {
        System.out.println("工程师: " + engineer.name + ", KPI: " + engineer.kpi);
    }

    @Override
    public void visit(ManagerStaff manager) {
        System.out.println("经理: " + manager.name + ", KPI: " + manager.kpi +
                ", 新产品数量: " + manager.getProducts());
    }
}