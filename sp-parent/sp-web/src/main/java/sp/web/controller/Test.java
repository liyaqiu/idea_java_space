package sp.web.controller;

/**
 * @author eric
 * @date 2022/4/11 11:51
 **/
public class Test {


    public static void main(String[] args) {
        Father father = new Son();
        System.out.println(father.name);
        father.hello();
    }
}

class Father{
    public  String name = "Father";
    public  void  hello(){
        System.out.println("father");
    }
}
class Son extends Father{
    public  String name = "Son";
    public  void  hello(){
        System.out.println("Son");
    }
}
