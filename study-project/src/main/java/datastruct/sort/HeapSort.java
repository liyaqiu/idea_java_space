package datastruct.sort;

public class HeapSort implements Sort {
    public static void main(String[] args) {
        int[] array = new int[]{4, 6, 8, 5, 9};
        new HeapSort().main(array);
        //94856
        //System.out.println(Arrays.toString(array));
    }

    @Override
    public void main(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            swap(array, array.length,i);
        }
        //System.out.println(Arrays.toString(array));
        //元素交换,只需要交换length-1次，因为root不需要交换
        for (int i = array.length-1; i >0 ; i--) {
            //System.out.println(i);
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            //System.out.println(Arrays.toString(array));
            //每做一次大顶堆交换都需要把最后一个元素替换
            swap(array, i,0);

        }
    }
    private void swap(int[] array,int length,int index){
        int temp = array[index];
        for (int i = index*2+1; i <length ; i = i*2+1) {
            //判断有没有右子节点,并且找出最大的子节点
            if (i + 1 < length && array[i] < array[i + 1]) {
                i++;
            }
            if (array[i] > temp) {
                array[index] = array[i];
                //改变节点
                index = i;
            } else {
                break;
            }
        }
        array[index] = temp;
    }

}
