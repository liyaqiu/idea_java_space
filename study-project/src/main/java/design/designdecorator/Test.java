package design.designdecorator;

import java.io.IOException;

public class Test  {



    public static void main(String[] args) throws IOException {
//        Pack pack = new BigPack();
//        pack.toPack();
//        System.out.println("------------------------");
//        Decorator decorator = new Book(pack);
//        decorator.toPack();
//        System.out.println("------------------------");
//        Rule rule = new Rule(decorator);
//        rule.toPack();



        for (Class<?> aClass : CCCCC.class.getInterfaces().clone()) {
            System.out.println(aClass);
        }

    }
}
interface AA{

}
interface BB extends  AA{}

class CCCCC implements BB{

}