package datastruct.arrayqueue;

public class Test {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.add(1);
        arrayQueue.add(2);
        arrayQueue.add(3);
        arrayQueue.showElements();
        System.out.println("移除-->"+arrayQueue.remove());
        System.out.println("头元素"+arrayQueue.watchHead());
        arrayQueue.add(4);
        arrayQueue.showElements();
        System.out.println("移除-->"+arrayQueue.remove());
        System.out.println("移除-->"+arrayQueue.remove());
        System.out.println("移除-->"+arrayQueue.remove());
        System.out.println("头元素"+arrayQueue.watchHead());


    }
}
