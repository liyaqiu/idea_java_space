package datastruct.sort;

import datastruct.find.ErFenFind;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] array = Utils.getArray(80000);
        System.out.println("开始....");
        for (int i = 0; i <2 ; i++) {
            System.out.println("---------------------------");
            //冒泡排序
            Sort sort = new MaoPaoSort();
            //sort.exec(array.clone(), "冒泡排序时间");
            //选择排序
            sort = new XuanZeSort();
            //sort.exec(array.clone(), "选择排序时间");
            //插入排序
            sort = new ChaRuSort();
            sort.exec(array.clone(), "插入排序时间");


            sort = new JiShuSort();
            sort.exec(array.clone(), "基数排序");
            //快速排序
            sort = new KuaiSuSort();
            sort.exec(array.clone(), "快速排序");

            sort = new GuiBingSort();
            sort.exec(array.clone(), "归并排序");

            sort = new HeapSort();
            sort.exec(array.clone(), "堆排序");
            //希尔排序
            sort = new XiErSort();
            sort.exec(array.clone(), "希尔排序时间");

            List<Integer> list = new ArrayList<>();
            for (int i1 = 0; i1 < array.clone().length; i1++) {
                list.add(array[i1]);
            }
            long startTime= 0L;
            long endTime = 0L;

            startTime = System.currentTimeMillis();
            Collections.sort(list, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 -o2;
                }
            });
            endTime = System.currentTimeMillis();
            System.out.println("时间："+((endTime-startTime)));


            //Collections.sort();
//            sort = new KuaiSuSort();
//            int[] clone = array.clone();//10457
//            sort.exec(clone, "快速排序");
            //verify(clone);

        }

    }
    static void verify(int[] array){
        //选择排序
        Sort sort = new XuanZeSort();
        sort.exec(array, "选择排序时间");
    }
}
