package design.designvisitor;

public class Test {
    public static void main(String[] args) {
        ObjectStruct objectStruct = new ObjectStruct();
        objectStruct.print(new ActionFail());
    }
}
