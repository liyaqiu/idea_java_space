package datastruct.linkedlist;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new SingleLinkedList.Node(4, "李雅秋4"));
        linkedList.add(new SingleLinkedList.Node(1, "李雅秋1"));
        linkedList.add(new SingleLinkedList.Node(2, "李雅秋2"));
        linkedList.add(new SingleLinkedList.Node(3, "李雅秋3"));
        linkedList.add(new SingleLinkedList.Node(6, "李雅秋6"));
        linkedList.showAll();
        System.out.println("------------");
        linkedList.remove(new SingleLinkedList.Node(6, "李雅秋6"));
        linkedList.showAll();

//
//        linkedList.reverseHead();
//
//        System.out.println("------------");
//       linkedList.showAll();
//        System.out.println("------------");
//       linkedList.reversePrint();





//        List<SingleLinkedList.Node> list = new LinkedList<>();
//        list.add(new SingleLinkedList.Node(2, "李雅秋2"));
//        list.add(new SingleLinkedList.Node(5, "李雅秋3"));
//        list.add(new SingleLinkedList.Node(3, "李雅秋3"));
//        list.add(new SingleLinkedList.Node(6, "李雅秋6"));
//        for (SingleLinkedList.Node node : list) {
//            System.out.println(node);
//        }
//
//        list.sort(new Comparator<SingleLinkedList.Node>() {
//            @Override
//            public int compare(SingleLinkedList.Node o1, SingleLinkedList.Node o2) {
//                if(o1.getId()>o2.getId()){
//                    return 1;
//                }
//                return -1;
//            }
//        });
//        for (SingleLinkedList.Node node : list) {
//            System.out.println(node);
//        }

    }
}
