package datastruct.sort;

import java.util.Arrays;

//希尔排序(shell排序)，也说插入排序的升级版本，目的说将大数往后移动，减少交换的次数
//先把元素分为n组，然后在每组元素之间调换位置，直到array.length/2!=0才停止分组
//希尔位移法之所以比交换法快，是因为交换的赋值次数太多倒置性能下降
public class XiErSort implements Sort {

    public static void main(String[] args) {
        int array[] = new int[]{8, 9, 1,7, 2, 3, 5, 4, 6, 0};
//        int[] array = new int[1000];
//        for (int i = 0; i < 1000; i++) {
//            array[i] = (int) (Math.random() * 80000);
//        }

        new XiErSort().main(array);
        System.out.println("-----------------------------");
        //XiErSort.shellSort2(array.clone());
        System.out.println(Arrays.toString(array));
    }

    //99,5,69,33,56,1
    //5 99 69 33 56 1
    //5 69 99 33 56 1
    //5 33 69 99 56 1
    //5 33 56 69 99 1
    //5 1  33 56 69 99
    //temp=22  j=6-->22   12
    //  3     12
    //  0     55
    public void main1(int[] array) {
        int interval = array.length;//15length
        //15/2=7 7/2=3 3/2=1 1/2=0
        while ((interval = interval / 2) != 0) {
            //步长3 3 6 9 12 15
            //8-3=5 5-3=2  2-3=-1
            for (int i = interval; i < array.length; i++) {
                for (int j = i; j >= interval; j -= interval) {
                    //array[j] 是最后元素
                    //array[j-interval] 是最后元素的前一个元素
                    if (array[j] < array[j - interval]) {
                        int temp = array[j];
                        array[j] = array[j - interval];
                        array[j - interval] = temp;
                    }
                }
            }
            System.out.println(Arrays.toString(array));
        }

//        for (int i = 0; i < array.length; i++) {
//            if(i+1== array.length){
//                System.out.println("顺序正常");
//                break;
//            }
//            if(array[i]>array[i+1]){
//                System.out.println(array[i]+"   "+array[i+1]);
//                break;
//            }
//        }
    }

    public void main(int[] array) {
        int interval = array.length;
        while ((interval = interval / 2) != 0) {
            for (int i = interval; i < array.length; i++) {
                int temp = array[i];
                int index = i;
                //第一次比较说一定有的，只需要判断边界
                while (temp<array[index-interval]){
                    //后面值  =  前面值  （前面值赋值给后一位）
                    array[index]= array[index-interval];
                    index-=interval;
                    //System.out.println("---------------------");
                    if(index-interval<0){
                        break;
                    }
                }
                array[index] = temp;
            }
        }
    }
    //5 6 8 4 2
    //5 6 4 8 2
    //5 4 6 8 2
    //4 5 6 8 2

    //5 6 8 4 2
    //4 5 6 8 2


    //0 2 1 4 3
    //0 2 1 4 3

    // 12  22 9
    //temp 9
    // array[j-intercal] 22  array[j] 9
    // 12  22
    //
    public static void shellSort2(int[] arr) {
        for (int interval = arr.length / 2; interval > 0; interval /= 2) {//先分为两个数据一组 在一点点合并
            for (int i = interval; i < arr.length; i++) {//一组一组处理
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - interval]) {
                    while (j - interval >= 0 && temp < arr[j - interval]) {//注意必须是val 比较一次后若再下一个数比val大则该数后移 否则val值的位置不变
                        arr[j] = arr[j - interval];
                        j -= interval;
                    }
                    arr[j] = temp;
                    //System.out.println(Arrays.toString(arr));
                }

            }
            System.out.println(Arrays.toString(arr));
        }
    }
    //[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
    //3 1 0 9 7
    //1 3 0 9 7
    //0 1 3 9 7
    //0 1 3 9 7
    //0 1 3 7 9
    //[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]

    //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
}
