package datastruct.linkedlist;

public class Test2 {
    public static void main(String[] args) {
//        DoubleLinkedList linkedList = new DoubleLinkedList();
//        linkedList.insert(new DoubleLinkedList.Node(7, "李雅秋1"));
//        linkedList.insert(new DoubleLinkedList.Node(6, "李雅秋2"));
//        linkedList.insert(new DoubleLinkedList.Node(3, "李雅秋3"));
//        linkedList.insert(new DoubleLinkedList.Node(5, "李雅秋5"));
//        linkedList.insert(new DoubleLinkedList.Node(4, "李雅秋4"));
//
//        linkedList.printHeadAll();
//        linkedList.printTailAll();

        Fath f = new Fath();
        f.fath = f;
        System.out.println(f);
        //linkedList.delete(5);
//        //linkedList.delete(5);
//        linkedList.printHeadAll();
//        linkedList.printTailAll();
//        linkedList.reverseHead();
//
//        linkedList.printHeadAll();
//        linkedList.printTailAll();
    }
}
class Fath{
    public  Fath fath;

    @Override
    public String toString() {
        return "Fath{" +
                "fath=" + fath +
                '}';
    }
}
