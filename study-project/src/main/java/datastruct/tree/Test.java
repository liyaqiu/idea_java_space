package datastruct.tree;

import datastruct.tree.binarytree.BinaryTree;

public class Test {
    public static void main(String[] args) {
        BinaryTree.Node root = new BinaryTree.Node("1", "李志平");

        BinaryTree.Node node1 = new BinaryTree.Node("3", "李小东");
        BinaryTree.Node node4 = new BinaryTree.Node("8", "李泳萱");
        BinaryTree.Node node5 = new BinaryTree.Node("10", "李民骏");
        node1.setLeft(node4);
        node1.setRigth(node5);

        BinaryTree.Node node3 = new BinaryTree.Node("6", "李小秋");
        BinaryTree.Node node6 = new BinaryTree.Node("14", "李梓睿");

        node3.setLeft(node6);
        root.setLeft(node1);
        root.setRigth(node3);
        //1 2 5 4 3 6 7
        BinaryTree binaryTree = new BinaryTree(root);
//        binaryTree.printPrefix(root);
//        System.out.println("----------------");
//        binaryTree.delNode("3",root);
//        System.out.println("----------------");
//        binaryTree.printPrefix(root);
        System.out.println("查找结果:"+binaryTree.findSuffix("14",root));
//        System.out.println("---------------------");
//        System.out.println(binaryTree.findInfix("1"));
//        System.out.println("---------------------");
//        System.out.println(binaryTree.findSuffix("1"));

    }
}
