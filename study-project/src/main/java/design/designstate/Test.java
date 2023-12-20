package design.designstate;

public class Test {
    public static void main(String[] args) {
        PrizePool prizePool = new PrizePool();
        for (int i = 0; i <5 ; i++) {
            System.out.println("--------------------------------");
            prizePool.subtractAmount();
            prizePool.randomAcquire();
        }
    }
}
