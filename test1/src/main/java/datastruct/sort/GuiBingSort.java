package datastruct.sort;

import java.util.Arrays;

public class GuiBingSort implements Sort{

    public static void main(String[] args) {
        int[] array = new int[]{0,1,2,6,4,5};
        int[] tempArray = new int[array.length];
        new GuiBingSort().sort(array, 0, array.length-1,tempArray);
        System.out.println(Arrays.toString(array));
    }

    void sort(int[] array,int left,int right,int[] tempArray){
        if(left<right){
            int mid = (left+right)/2;
            sort(array,left,mid,tempArray);
            sort(array,mid+1,right,tempArray);
            //比较左右数组，然后填充在temp数组
            int tempIndex = 0;
            int leftIndex = left;
            int midIndex = mid+1;
            while (leftIndex<=mid&&midIndex<=right){
                if(array[leftIndex]<array[midIndex]){
                    tempArray[tempIndex] = array[leftIndex];
                    leftIndex++;
                }else{
                    tempArray[tempIndex] = array[midIndex];
                    midIndex++;
                }
                tempIndex++;
            }
            //数组左边剩余元素填充到temp数组
            while (leftIndex<=mid){
                tempArray[tempIndex] = array[leftIndex];
                tempIndex++;
                leftIndex++;
            }
            //数组右边剩余元素填充到temp数组
            while (midIndex<=right){
                tempArray[tempIndex] = array[midIndex];
                tempIndex++;
                midIndex++;
            }
            //数组复制
            tempIndex = 0;
            for (int i = left; i <=right ; i++) {
                array[i] = tempArray[tempIndex];
                tempIndex++;
            }
        }
    }

    @Override
    public void main(int[] array) {
        sort(array, 0, array.length-1, new int[array.length]);
    }
}
