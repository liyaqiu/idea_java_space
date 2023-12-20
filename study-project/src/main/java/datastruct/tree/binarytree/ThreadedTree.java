package datastruct.tree.binarytree;

public class ThreadedTree {
    private Node preNode;
    Node node;

    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node3 = new Node("3");
        Node node8 = new Node("8");
        Node node10 = new Node("10");
        node3.setLeft(node8);
        node3.setRight(node10);
        Node node6 = new Node("6");
        Node node14 = new Node("14");
        node6.setLeft(node14);
        node1.setLeft(node3);
        node1.setRight(node6);
        new ThreadedTree().inFixThreadedTree(node1);
//        Node temp = node6;
//        System.out.println(temp);
//        System.out.println(temp.getLeft());
//        System.out.println(temp.getRight());

        new ThreadedTree().printInfixThreaded(node1);

    }

    //前序线索化二叉树  1 3 8 10 6 14 (8 10 14 6)
    // 8 前驱 3    后继 10
    //10 前驱 8    后继 6
    //14 前驱 6    后继 null
    // 6 前驱 *    后继 14   1 3 6
//    public void preFixThreadedTree2(Node node) {
//        if (node != null) {
//            return;
//        }
//        //前驱设置
//        if (preNode != null && node.getLeft() == null) {
//            node.setLeft(preNode);
//            node.setLeftType(1);
//        }
//        //后继设置  // 8 前驱 3    后继 10
//        //10 前驱 8    -->后继 6
//        if (preNode != null && preNode.getRight() != null) {
//            node.setRight(preNode.getRight());
//            node.setRightType(1);
//        }
//        10 preNode = node;
//        preFixThreadedTree2(node.getLeft());
//        preFixThreadedTree2(node.getRight());
//
//
//    }


    //打印中序线索化二叉树
    public void printInfixThreaded(Node node){
        while (node!=null){
            //循环找前驱线索对象
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //输出线索对象
            System.out.print(node.getId()+" ");
            //输出当前线索对象的后继节点
            while (node.getRightType() ==1){
                node = node.getRight();
                System.out.print(node.getId()+" ");
            }
            node = node.getRight();
        }
    }


    public void inFixThreadedTree(Node node) {
        inFixThreadedTree1(node);
        preNode = null;
    }

    //中序线索化二叉树  8 3 10 1 14 6 (8 10 14 6)
    // 8 前驱 null 后继 3
    //10 前驱 3    后继 1
    //14 前驱 1    后继 6
    // 6 前驱 *    后继 null
    public void inFixThreadedTree1(Node node) {
        if (node == null) {
            return;
        }
        inFixThreadedTree1(node.getLeft());
        //前驱线索化
        if (node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }
        //后继线索化
        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
        inFixThreadedTree1(node.getRight());
    }


    public static class Node {
        private String id;
        private Node left;
        private int leftType;
        private Node right;
        private int rightType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setRightType(int rightType) {
            this.rightType = rightType;
        }

        public Node(String id) {
            this.id = id;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id='" + id + '\'' +
                    ", leftType=" + leftType +
                    ", rightType=" + rightType +
                    '}';
        }
    }
}
