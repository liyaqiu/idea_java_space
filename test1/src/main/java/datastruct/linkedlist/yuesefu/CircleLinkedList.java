package datastruct.linkedlist.yuesefu;

public class CircleLinkedList {
    private Node headNode;
    private Node tailNode;




    public void manyPeopleRun(int count) throws InterruptedException {
        if (headNode == null) {
            return;
        }
        Node current = headNode;
        Node prve = null;
        int counter = 0;
        while (true) {
            Thread.sleep(100);
            counter++;
            System.out.println(counter);
            if (counter >= count) {
                counter = 0;
                System.out.println("出队" + current);
                if (prve == current) {
                    headNode = null;
                    tailNode = null;
                    break;
                }
                prve.next = current.next;
                //prve = current.next;
                current = current.next;
                continue;
            }
            prve = current;
            current = current.next;
        }
    }


    public void insert(Node node) {
        if (headNode == null) {
            headNode = node;
            tailNode = node;
            node.next = node;
        }
        tailNode.next = node;
        node.next = headNode;
        tailNode = node;
    }

    public void printFirstCycle() throws InterruptedException {
        Node temp = headNode;
        while (true) {
            System.out.println(temp);
            if (temp.next == headNode) {
                break;
            }
            temp = temp.next;
        }
    }

    public void printCycle() throws InterruptedException {
        Node temp = headNode;
        while (true) {
            Thread.sleep(1000);
            System.out.println(temp);
            temp = temp.next;
        }
    }


    public static class Node {
        private int id;
        private String name;
        private Node next;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
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
