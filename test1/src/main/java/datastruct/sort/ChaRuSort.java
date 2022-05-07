package datastruct.sort;

import java.util.Arrays;

//插入排序  后半部分小的数据越多，耗时越长
public class ChaRuSort implements Sort{
    public  void main(int[] array) {
        //int[] array  = new int[]{2,1,5,3,7,6,0};
        //{2}{1,5,3,7,0}
        //{1,2}{5,3,7,0}
        //{1,2,5}{3,7,0}
        //{1,2,3,5}{7,0}
        //-----------------------------------------
        //{1,2,3,5,7}{0}
        //{0,2,3,5,7}{1}
        //{0,1,3,5,7}{2}
        //{0,1,2,5,7}{3}
        //{0,1,2,3,7}{5}
        //{0,1,2,3,5}{7}
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if(array[j]> array[i]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(array));
    }

}

