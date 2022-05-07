package design.designcompose;

public class Test1 {
    public static void main(String[] args) {
        Son1 son1 = new Son1("李志平");

        Son3 son3_1 = new Son3("李梓睿");
        Son3 son3_2 = new Son3("李庆乐");
        Son3 son3_3 = new Son3("李泳萱");
        Son3 son3_4 = new Son3("李民俊");

        Son2 son2_1 = new Son2("李小秋");
        son2_1.add(son3_1);
        son2_1.add(son3_2);

        Son2 son2_2 = new Son2("李小东");
        son2_2.add(son3_3);
        son2_2.add(son3_4);

        son1.add(son2_1);
        son1.add(son2_2);


        son2_1.print();


    }
}
