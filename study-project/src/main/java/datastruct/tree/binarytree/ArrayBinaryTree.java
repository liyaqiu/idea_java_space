
package datastruct.tree.binarytree;

/**
 * 顺序存储二叉树（完全二叉树 或者是 满二叉树）
 * 当前节点 左节点(index*2+1)
 * 当前节点 右节点(index*2+2)
 * 当前节点的父节点 ((index-1)/2)
 */
public class ArrayBinaryTree {
    public static void main(String[] args) {
        new ArrayBinaryTree().printPrefix(0);
        System.out.println();
        new ArrayBinaryTree().printInfix(0);
        System.out.println();
        new ArrayBinaryTree().printSuffix(0);
    }

    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};

    //打印前序二叉树 1 2 4 5 3 6 7
    public void printPrefix(int index) {
        System.out.print(array[index] + " ");
        if (index * 2 + 1 < array.length) {
            printPrefix(index * 2 + 1);
        }
        if (index * 2 + 2 < array.length) {
            printPrefix(index * 2 + 2);
        }
    }

    //打印中序二叉树 4 2 5 1 6 3 7
    public void printInfix(int index) {
        if (index * 2 + 1 < array.length) {
            printInfix(index * 2 + 1);
        }
        System.out.print(array[index]+" ");
        if (index * 2 + 2 < array.length) {
            printInfix(index * 2 + 2);
        }
    }

    //打印后序二叉树4 5 2 6 7 3 1
    public void printSuffix(int index) {
        if(index*2+1<array.length){
            printSuffix(index*2+1);
        }
        if(index*2+2<array.length){
            printSuffix(index*2+2);
        }
        System.out.print(array[index]+" ");
    }
}
