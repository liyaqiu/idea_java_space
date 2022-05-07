package datastruct.linkedlist.yuesefu;

import java.util.Arrays;
import java.util.Stack;

public class Test {
    public static void main(String[] args) throws Exception{
        //System.arraycopy(elementData, index + 1, elementData, index, j);
        Integer[] array = new Integer[]{1,2,3,4,5};

        System.out.println(Arrays.toString(array));


        CircleLinkedList cycleLinkedList = new CircleLinkedList();
        cycleLinkedList.insert(new CircleLinkedList.Node(1, "李雅秋1"));
        cycleLinkedList.insert(new CircleLinkedList.Node(2, "李雅秋2"));
        cycleLinkedList.insert(new CircleLinkedList.Node(3, "李雅秋3"));
        cycleLinkedList.insert(new CircleLinkedList.Node(4, "李雅秋4"));
        cycleLinkedList.insert(new CircleLinkedList.Node(5, "李雅秋5"));
        cycleLinkedList.printFirstCycle();
        cycleLinkedList.manyPeopleRun(3);
//        cycleLinkedList.insert(new CycleLinkedList.Node(6, "李雅秋6"));
//        cycleLinkedList.insert(new CycleLinkedList.Node(7, "李雅秋7"));
//        cycleLinkedList.printFirstCycle();
//        cycleLinkedList.manyPeopleRun();
    }

}
