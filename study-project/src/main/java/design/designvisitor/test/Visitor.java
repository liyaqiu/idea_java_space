package design.designvisitor.test;

public interface Visitor {

    // 访问工程师类型
    void visit(EngineerStaff engineer);

    // 访问经理类型
    void visit(ManagerStaff manager);
}