package datastruct.sort;

import java.util.Arrays;

public class JiShuSort implements Sort {
    public static void main(String[] args) {
        int[] array = new int[]{53, 3, 542, 748, 14,214};
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i]/-1;
        }
//        new JiShuSort().main(array);
        System.out.println(Arrays.toString(array));
        System.out.println(-1/1);
    }

    @Override
    public void main(int[] array) {
        int[][] temps = new int[10][array.length];
        int[] indexs = new int[10];

        //求出最大位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        //求出是几位数,有几位就循环几次
        int length = (max + "").length();

        for (int i = 0,divide = 1; i < length; i++,divide*=10) {
            //将数据放入桶中
            for (int j = 0; j < array.length; j++) {
                //求出放在第几个桶
                int index = array[j]/divide%10;
                temps[index][indexs[index]] = array[j];
                indexs[index]++;
            }
            //用于记录原始数据的数据到了哪个位置
            int arrayIndex = 0;
            //将桶数据取出来
            for (int j = 0; j < indexs.length ; j++) {
                if(indexs[j]>0){
                    for (int k = 0; k < indexs[j]; k++) {
                        array[arrayIndex] = temps[j][k];
                        arrayIndex++;
                    }
                    indexs[j] = 0;
                }
            }
        }
    }
}
