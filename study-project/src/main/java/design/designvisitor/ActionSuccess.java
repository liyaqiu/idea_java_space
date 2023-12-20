package design.designvisitor;

public class ActionSuccess implements Action{

    @Override
    public void getManResult(Person person) {
        System.out.println("获得成功男人:" + person.getName() + "的结果");
    }

    @Override
    public void getWomanResult(Person person) {
        System.out.println("获得成功女人:" + person.getName() + "的结果");
    }
}
