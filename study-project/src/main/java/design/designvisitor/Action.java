package design.designvisitor;

public interface Action {
    //获取男人结果
    void getManResult(Person person);
    //获取女人结果
    void getWomanResult(Person person);
}
