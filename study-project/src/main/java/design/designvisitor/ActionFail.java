package design.designvisitor;

public class ActionFail implements Action{

    @Override
    public void getManResult(Person person) {
        System.out.println("获得男人失败:" + person.getName() + "的结果");
    }

    @Override
    public void getWomanResult(Person person) {
        System.out.println("获得女人失败:" + person.getName() + "的结果");
    }
}
