package datastruct.linkedlist;

public class SingleLinkedList {
    private Node headNode = new Node();

    //从尾部追加节点
/*    public void add(Node node) {
        Node temp = headNode;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }*/

    //插入顺序节点
    public void add(Node node) {
        Node temp = headNode;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                break;
            }
            if(temp.next.id >= node.id ){
                node.next = temp.next;
                temp.next = node;
                break;
            }
            temp = temp.next;
        }
    }


    public boolean isEmpty() {
        if (headNode.next == null) {
            return true;
        }
        return false;
    }

    public void showAll() {
        if (isEmpty()) {
            System.out.println("当前链表为空....");
        }
        Node temp = headNode.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
    public void reversePrint(){
        print(headNode.next);
    }

    void print(Node node){
        if(node.next==null){
            System.out.println(node.id+"  "+node.name);
            return;
        }
        print(node.next);
        System.out.println(node.id+"  "+node.name);
    }

    public void reverseHead(){
        if(headNode.next==null || headNode.next.next==null){
            return;
        }
        Node currentHead = headNode.next;
        Node newHead = new Node();
        while (true){
            if(currentHead==null){
                break;
            }

            Node temp = currentHead.next;
            currentHead.next = null;
            insertHeadBefore(newHead,currentHead);
            currentHead = temp;
        }
        headNode.next = newHead.next;
    }
    //每次一个节点插入head之后
    private void insertHeadBefore(Node newHead,Node node){
        Node temp = newHead.next;
        newHead.next = node;
        node.next = temp;
        return;
    }


    public void remove(Node node) {
        Node temp = headNode;
        while (true){
            if(temp.next ==null){
                break;
            }
            if(temp.next.id==node.id){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public static class Node{
        private int id;
        private String name;
        private Node next;

        public Node() {
        }

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
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


