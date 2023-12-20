package datastruct.sparsearray;

public class SparseArrayToArray {
    int[][] sparse ;
    int row;
    int cell;
    int valCount;
    int[][] array;
    public SparseArrayToArray(int[][] sparse) {
        this.sparse = sparse;
        row = sparse[0][0];
        cell = sparse[0][1];
        valCount = sparse[0][2];
        array = new int[row][cell];
    }

    public void printArray(){
        for (int[] rows : array) {
            for (int cell : rows) {
                System.out.printf("%d\t",cell);
            }
            System.out.println();
        }
    }

    public void transform(){
        for (int i = 1; i < sparse.length; i++) {
            array[sparse[i][0]][sparse[i][1]] = sparse[i][2];
        }
        for (int[] rows : array) {
            for (int cell : rows) {
                System.out.printf("%d\t",cell);
            }
            System.out.println();
        }
    }




}
