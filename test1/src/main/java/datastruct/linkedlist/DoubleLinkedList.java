package datastruct.linkedlist;


//双向链表
public class DoubleLinkedList {
    private Node head = new Node();
    private Node tail;

    public void insert(Node node){
        Node temp = head;
        while (true){
            if(temp.next == null){
                temp.next = node;
                node.prev = temp;
                tail = node;
                break;
            }
            if(temp.next.id > node.id){
                temp.next.prev = node;
                node.next = temp.next;
                node.prev = temp;
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }

    public void printHeadAll(){
        Node temp = head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
        System.out.println("-----------------------");
    }
    public void printTailAll(){
        if(tail == null){
            System.out.println("没有尾部");
        }
        Node temp = tail;
        while (true){
            if(temp.prev==null){
                break;
            }
            System.out.println(temp);
            temp = temp.prev;
        }
        System.out.println("-----------------------");
    }

    public void delete(int i){
        Node temp = head.next;
        while (true){
            if(temp==null){
                System.out.println("没有元素可以删除");
                break;
            }
            if(temp.id == i){
                temp.prev.next = temp.next;
                if(temp.next ==null ){
                    tail = temp.prev ;
                    break;
                }
                temp.next.prev = temp.prev;

                break;
            }
            temp = temp.next;
        }
    }
//    public void delete(int i){
//        Node temp = head;
//        while (true){
//            if(temp.next==null){
//                System.out.println("没有元素可以删除");
//                break;
//            }
//            if(temp.next.id == i){
//                if(temp.next.next ==null ){
//                    tail = temp ;
//                    temp.next = null;
//                    break;
//                }
//                temp.next.next.prev = temp;
//                temp.next = temp.next.next;
//                break;
//            }
//            temp = temp.next;
//        }
//    }

    public void reverseHead(){
        Node newHead = new Node();
        Node temp = head.next;
        while (true){
            if(temp==null){
                break;
            }
            Node nextNode = temp.next;
            temp.next = null;
            insertNodeAfter(newHead, temp);
            temp = nextNode;
        }
        head.next =newHead.next;
        newHead.next.prev = head;
        head = newHead;

    }

    private void insertNodeAfter(Node newHead,Node node){
        if(newHead.next ==null){
            node.prev = newHead;
            newHead.next = node;
            tail = node;
            return;
        }
        Node temp = newHead.next;
        newHead.next = node;
        node.prev = newHead;
        node.next = temp;
        temp.prev = node;
    }


    public static class Node{
        private int id;
        private String name;
        private Node next;
        private Node prev;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
