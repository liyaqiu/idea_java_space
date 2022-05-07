package datastruct.sort;

import java.util.Random;

public class Utils {
    public static int[] getArray(int max) {
        int[] array = new int[max];
        int count = 0;
        for (int i = 0; i < max; i++) {
            array[i] = (int)(Math.random()*80000);
            //array[i]=count++;
        }
        return array;
    }
}
