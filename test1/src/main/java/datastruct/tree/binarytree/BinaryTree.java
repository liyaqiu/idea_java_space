package datastruct.tree.binarytree;

import java.util.concurrent.atomic.AtomicBoolean;

public class BinaryTree {
    private Node root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public void delNode(String id, Node node) {
        delNode(id, node, new AtomicBoolean(false));
    }

    private void delNode(String id, Node node, AtomicBoolean bl) {
        if (node == null) {
            return;
        }
        if (node.left != null && node.left.id.equals(id)) {
            node.left = null;
            bl.set(true);
        }
        delNode(id, node.left, bl);
        //只能说减少部分编译，不能完全避免
        if (bl.get()) {
            return;
        }

        if (node.rigth != null && node.rigth.id.equals(id)) {
            node.rigth = null;
            bl.set(true);
        }
        delNode(id, node.rigth, bl);
    }

    //前序打印
    public void printPrefix(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        printPrefix(node.getLeft());
        printPrefix(node.getRigth());
    }

    //中序打印
    public void printInfix(Node node) {
        if (node == null) {
            return;
        }
        printPrefix(node.getLeft());
        System.out.println(node);
        printPrefix(node.getRigth());
    }

    //后续打印
    public void printSuffix(Node node) {
        if (node == null) {
            return;
        }
        printPrefix(node.getLeft());
        printPrefix(node.getRigth());
        System.out.println(node);
    }

    //前序查找 1 3 8 10 6 14
    public Node findPrefix(String id, Node node) {
        if (node == null) {
            return null;
        }
        Node temp = null;
        System.out.print(node.id+" ");
        if (node.id.equals(id)) {
            return node;
        }
        temp = findPrefix(id, node.left);
        if (temp != null) {
            return temp;
        }
        temp = findPrefix(id, node.rigth);
        return temp;
    }

    //中序查找 8 3 10 1 14 6
    public Node findInfix(String id,Node node) {
        if (node == null) {
            return null;
        }
        Node temp =null;
        temp = findInfix(id, node.left);
        System.out.print(node.id+" ");
        if(node.id.equals(id)){
            return node;
        }
        if (temp!=null) {
            return temp;
        }
        temp = findInfix(id, node.rigth);
        return temp;
    }

    //后序查找 8 10 3 14 6 1
    public Node findSuffix(String id,Node node) {
        if (node == null) {
            return null;
        }
        Node temp = null;

        temp = findSuffix(id, node.left);
        if(temp!=null){
            return temp;
        }
        temp = findSuffix(id, node.rigth);
        System.out.print(node.id+" ");
        if(node.id.equals(id)){
            return node;
        }
        return temp;
    }

    public static class Node {
        private String id;
        private String name;
        private Node left;
        private Node rigth;

        public Node(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRigth() {
            return rigth;
        }

        public void setRigth(Node rigth) {
            this.rigth = rigth;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}




