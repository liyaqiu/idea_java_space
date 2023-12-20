package datastruct.tree.binarytree;

import java.util.ArrayList;
import java.util.Collections;

//赫夫曼树构建
public class HeFuManTree {
    public static void main(String[] args) {
//        int[] array = new int[]{13, 7, 8, 3, 29, 6, 1};
//        HeFuManTree heFuManTree = new HeFuManTree();
//        Node tree = heFuManTree.bulidTree(array);
//        heFuManTree.prefixPrint(tree);
//        System.out.println();

        //System.out.println(new String("我".getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.ISO_8859_1));
        //System.out.println((int)'我');

//        char a = 65535;
//        System.out.println((byte)a);
//        System.out.println(Byte.parseByte("11111111"));
       // System.out.println(Byte.parseByte("11111110",2));
        //System.out.println(Integer.parseInt("01000000000000000000000000000010",2));
        //byte b7 = 0b10000001; //solves to the value -127
        //System.out.println(b7);
        //System.out.println(Integer.toBinaryString(-2));
//        System.out.println(Byte.parseByte("01111111", 2));
//        System.out.println(Integer.parseInt("11111111111111111111111111111110",2));

        byte b =(byte)Integer.parseInt("011100",2);
        System.out.println(b);
        int i = b;
        //i|=256;
       System.out.println(Integer.toBinaryString(28));

//        System.out.println(b);
       // System.out.println("111".getBytes(StandardCharsets.UTF_8).length);
    }

    //前序打印67 29 38 15 7 8 23 10 4 1 3 6 13
    public void prefixPrint(Node node){
        if(node==null){
            return;
        }
        System.out.print(node.value);
        prefixPrint(node.left);
        prefixPrint(node.right);

    }

    //构建赫夫曼树
    public Node bulidTree(int[] array) {
        ArrayList<Node> list = new ArrayList<>();
        for (int i : array) {
            list.add(new Node(i));
        }

        while (list.size() > 1) {
            Collections.sort(list, (x, y) -> x.value - y.value);
            Node left = list.get(0);
            Node right = list.get(1);
            Node node = new Node(left.value + right.value);
            node.left = left;
            node.right = right;
            list.remove(left);
            list.remove(right);
            list.add(node);
        }
        return list.get(0);
    }

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
