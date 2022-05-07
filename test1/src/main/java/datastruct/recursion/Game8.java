package datastruct.recursion;

public class Game8 {
    int size = 8;
    int[] array = new int[size];

    public static void main(String[] args) {
        new Game8().run(0);
    }


    private void run(int n) {
        //每次到第8个后就打印答案
        if (n == size) {
            print();
            return;
        }
        //第n个皇后的值从0-7
        for (int i = 0; i < size; i++) {
            array[n] = i;
            if (conflict(n)) {
                run(n + 1);
            }
        }
    }
    //行不需要判断，数组的每一个值代表1行
    private boolean conflict(int n) {
        for (int i = 0; i < n; i++) {
            //判断列 和 左右对角线
            if (array[n] == array[i] || n - i == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
