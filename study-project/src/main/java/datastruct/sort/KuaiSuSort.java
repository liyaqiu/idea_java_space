package datastruct.sort;

import java.util.Arrays;

public class KuaiSuSort implements Sort {
    public static void main(String[] args) {

        //[2, 0, 2, 3, 3, 2, 2, 3, 3, 3]
        //[0, 7, 2, 3, 6, 2, 2, 3, 3, 3]
        int[] array = new int[]{6, 7, 2, 3, 0, 4, 1, 9, 8, 5};
        System.out.println(Arrays.toString(array));
        //int[] array = new int[]{0,0,0,0,2,1,1,1,1,1};
        new KuaiSuSort().main(array);

    }

    @Override
    public void main(int[] array) {
        quickSort(array, 0, array.length - 1);
        //[2, 0, 2, 3, 2]
        //[0, 2, 2, 3, 2]

    }

    void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            //System.out.println("left:"+left+"  rigth:"+right);
            return;
        }
        //System.out.println(Arrays.toString(array));
        int l = left;
        int r = right;

        int middle = array[left];

        while (l != r) {
            //如果以左为基准值，必须从右开始比较
            while (l < r && array[r] >= middle) {
                r--;
            }
            //System.out.println(r);
            while (l < r && array[l] <= middle) {
                l++;
            }

            if(l<r){
                int temp = array[l];
                array[l] = array[r];
                array[r] = temp;
            }
        }
        //System.out.println(l);
        //System.out.println("L->"+l+" R->"+r);
        array[left] = array[l];
        array[l] = middle;
        //System.out.println(Arrays.toString(array));
        //因为基数已经归位，除开基数继续计算
        quickSort(array, left,l-1 );
        quickSort(array, l+1, right);
    }

//
//    void quickSort1(int[] array, int left, int right) {
//        int l = left;
//        int r = right;
//        int middle = array[(left + right) / 2];
//        //System.out.println(middle);
//        while (l < r) {
//            //这里不能等于，如果等于中间值就会越界
//            while (array[l] < middle) {
//                l++;
//            }
//            while (array[r] > middle) {
//                r--;
//            }
//            //如图的判断是针对如下代码进行退出，如果左右指针相交，退出循环
//           /* while (array[l]<middle){
//                l++;
//            }
//            while (array[r]>middle){
//                r--;
//            }*/
//            if (l >= r) {
//                //System.out.println("L:"+l+"  R:"+r);
//                break;
//            }
//
//            //如果相同的值较多，可以使用if(arr[l]!=arr[r])在进行交换
//            int temp = array[l];
//            array[l] = array[r];
//            array[r] = temp;
//            //右边的值存在2种可能性
//            //右边调换左边  L<=中间值  R>=中间值
//            if (array[l] == middle) {
//                l++;
//            }
//            if (array[r] == middle) {
//                r--;
//            }
//
//        }
////        if(l==r){
////            l++;
////            r--;
////        }
//        System.out.println("L:" + l + "  R:" + r);
//        //向左递归 left<r确保有2个元素，0<1   arr[0] arr[1]
////        if(left<r){
////            //System.out.println("向左递归");
////            quickSort(array, left, r);
////        }
////        if(right>l){
////            //System.out.println("向右递归");
////            quickSort(array, l, right);
////        }
//    }

}
