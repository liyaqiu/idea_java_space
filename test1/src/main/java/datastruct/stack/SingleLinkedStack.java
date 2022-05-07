package datastruct.stack;

import javax.naming.OperationNotSupportedException;

public class SingleLinkedStack implements Stack<Integer> {
    private Node head = new Node();
    private int maxsize;
    private int counter;

    public SingleLinkedStack(int maxsize) {
        this.maxsize = maxsize;
    }

    @Override
    public void push(Integer i) {
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        Node temp = head;
        while (true){
            if(temp.getNext() == null){
                Node node = new Node(i);
                temp.setNext(node);
                counter++;
                break;
            }
            temp = temp.getNext();
        }
    }

    @Override
    public boolean isFull()  {
        if(counter == maxsize){
            return true;
        }
        return false;
    }


    @Override
    public Integer pop() {
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        Node temp = head;
        Node prev = null;
        while (true){
            if(temp.getNext() == null){
                counter--;
                int i = temp.getI();
                prev.setNext(null);
                return i;
            }
            prev = temp;
            temp = temp.getNext();
        }
    }


    @Override
    public boolean isEmpty() {
        if(head.getNext()==null){
            return true;
        }
        return false;
    }

    @Override
    public void showAll() {
        if(isEmpty()){
            return;
        }
        printNode(head.getNext());
        System.out.println("---------------");
    }

    @Override
    public Integer peek() {
        if(isEmpty()){
            throw new RuntimeException("没有数据");
        }
        Node tmp = head;
        while (true){
            if(tmp.getNext() == null){
                return tmp.getI();
            }
            tmp = tmp.getNext();
        }
    }

    private void printNode(Node node){
        if(node.getNext()!=null){
            printNode(node.getNext());
        }
        System.out.println(node);
    }


    private class Node{
        private Node next;
        private int i;

        public Node(int i) {
            this.i = i;
        }

        public Node() {
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "i=" + i +
                    '}';
        }
    }
}
