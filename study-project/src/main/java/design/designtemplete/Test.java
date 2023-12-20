package design.designtemplete;

public class Test {
    public static void main(String[] args) {
        IMilk milk = new ChunMilk();
        milk.operate();


        IMilk milk1 = new NiuMilk();
        milk1.operate();
    }
}
