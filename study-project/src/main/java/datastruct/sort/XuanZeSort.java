package datastruct.sort;

//选择排序
public class XuanZeSort implements Sort {
    public  void main(int[] array) {
        //int[] array = new int[]{1,0,2,4,3,5,6,7};
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    System.out.println("1111111111");
                }
            }
            //System.out.println(Arrays.toString(array));
        }

    }
}
