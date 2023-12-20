package datastruct.find;

//使用二分查找必须是有序数组
public class ErFenFind {
    static int count = 0;

    public static void main(String[] args) {
        int[] array = new int[]{1, 2};
        System.out.println(new ErFenFind().find_pt(array, 1, 0, array.length - 1));
//        System.out.println(2/2);
//        System.out.println(2*0.5);
    }

    //非递归方式
    public int find_pt(int[] array, int val, int left, int right) {
        //int[] array = new int[]{1,12,56,65,77,82,91,103,104,105,111};
        while (left <= right) {
            int mid = (left + right) / 2;
            //int mid = left+(right-left) * (val-array[left])/(array[right]-array[left]);
            if (val == array[mid]) {
                return array[mid];
            } else if (val < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        } //mid = 0, 0 ,-1    1,0
        return -1;
    }

    //递归方式实现
    public int find_pt1(int[] array, int val, int left, int right) {
        if (left > right) {
            return -1;
        } //mid = 0, 0 ,-1    1,0
        int mid = (left + right) / 2;
        //int mid = left+(right-left) * (val-array[left])/(array[right]-array[left]);
        if (val == array[mid]) {
            return array[mid];
        } else if (val < array[mid]) {
            return find_pt1(array, val, left, mid - 1);
        } else {
            return find_pt1(array, val, mid + 1, right);
        }
    }


    public int insertValueSearch(int[] arr, int left, int right, int findVal) {
        //注意：findVal < arr[0]  和  findVal > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }

        // 求出mid, 自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        System.out.println("mid:" + mid);
        System.out.println("arr[mid]" + arr[mid]);
        int midVal = arr[mid];
        if (findVal > midVal) { // 说明应该向右边递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) { // 说明向左递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }

    }


}
