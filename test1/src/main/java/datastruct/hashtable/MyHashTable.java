
package datastruct.hashtable;

import java.util.HashMap;
import java.util.HashSet;

public class MyHashTable<T> {
    int size;
    MyHashSet<T>[] myHashSets;
    public MyHashTable(int capacity){
        this.size = capacity;
        myHashSets = new MyHashSet[size];
        for (int i = 0; i <size ; i++) {
            myHashSets[i] = new MyHashSet<T>();
        }
    }

    public void add(T t){
        myHashSets[hashFun(t)].add(t);
    }

    public void remove(T t){
        myHashSets[hashFun(t)].add(t);
    }

    public void showList(){
        for (int i = 0; i < myHashSets.length; i++) {
            System.out.println("第" + i + "个链表");
            myHashSets[i].showList();
        }
    }

    private int hashFun(T t){
       return t.hashCode()%size;
    }

}





class MyHashSet<T>{
    private Node<T> head = new Node<>();

    public void add(T t){
        Node<T> node = head;
        while (true){
            if(node.next == null){
                if(isExist(t)){
                    break;
                }
                node.next = new Node<T>(t);
                break;
            }
            node = node.next;
        }
    }

    public boolean isEmpty(){
        if(head.next==null){
            return false;
        }
        return true;
    }

    public boolean isExist(T t){
        if (isEmpty()){
            Node<T> temp = head;
            while (true){
                if(temp.next ==null){
                    break;
                }
                if(temp.next.value.hashCode() == t.hashCode() && temp.next.value.equals(t)){
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public boolean remove(T t){
        if (isEmpty()){
            Node<T> temp = head;
            while (true){
                if(temp.next ==null){
                    break;
                }
                if(temp.next.hashCode() == t.hashCode() && temp.next.equals(t)){
                    temp.next = temp.next.next;
                    return true;
                }
                temp = temp.next;
            }
        }
        return false;
    }

    public void showList(){
        if(isEmpty()){
            Node<T> temp = head;
            while (true){
                if(temp.next ==null){
                    break;
                }
                System.out.println(temp.next.value);
                temp = temp.next;
            }
        }
    }

    private static class Node<T>{
        T value;
        Node<T> next;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    }
}