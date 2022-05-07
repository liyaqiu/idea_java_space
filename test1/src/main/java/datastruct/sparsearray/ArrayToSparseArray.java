package datastruct.sparsearray;

import java.util.Random;

public class ArrayToSparseArray {
    private int rowsize = 10;
    private int cellsize = 10;
    private int[][] initArr = new int[rowsize][cellsize];
    {
        Random random = new Random();
        for (int i = 0; i <30 ; i++) {
            initArr[random.nextInt(10)][random.nextInt(10)] = random.nextInt(3);
        }
    }

    //打印初始化数组
    public void printInitArray(){
        for (int[] rows : initArr) {
            for (int cell : rows) {
                System.out.printf("%d\t",cell);
            }
            System.out.println();
        }
    }

    public int[][]  transfrom(){
        int valCount = 0;
        for (int[] rows : initArr) {
            for (int cell : rows) {
                if(cell!=0){
                    valCount++;
                }
            }
        }
        int counter = 1;
        int[][] sparse = new int[valCount+1][3];
        //给稀疏数组首行赋值
        sparse[0][0] = rowsize;
        sparse[0][1] = cellsize;
        sparse[0][2] = valCount;

        for (int i = 0; i < rowsize; i++) {
            for (int j = 0; j < cellsize; j++) {
                if(initArr[i][j]!=0){
                    sparse[counter][0] = i;
                    sparse[counter][1] = j;
                    sparse[counter][2] = initArr[i][j];
                    counter++;
                }
            }
        }
        printSparseArray(sparse);
        return sparse;
    }

   private void printSparseArray(int[][] sparse){
       for (int[] rows : sparse) {
           for (int cell : rows) {
               System.out.printf("%d\t",cell);
           }
           System.out.println();
       }
   }
}
