package datastruct.sort;

import java.util.Arrays;
//冒泡排序
public class MaoPaoSort implements Sort{
    public static void main(String[] args) {
        int[] array = new int[]{0,1,2,3,4,5,6,7};
        //new MaoPaoSort().main(array);
        array[0]++;
        System.out.println(10%10);
        System.out.println(748%100);
    }
    public  void main(int[] array) {
        boolean flag =false;
        for (int i = 0; i < array.length-1; i++) {
            flag =true;
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j]>array[j+1]){
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] =temp;
                    flag =false;
                }
            }
            //System.out.println(i+"  "+Arrays.toString(array));
            if(flag){
                break;
            }
        }

    }
}
