package datastruct.sparsearray;

public class Test {
    public static void main(String[] args) {
        ArrayToSparseArray arrayToSparseArray = new ArrayToSparseArray();
        System.out.println("-----------------------");
        arrayToSparseArray.printInitArray();
        int[][] sparseArray = arrayToSparseArray.transfrom();

        System.out.println("-----------------------");
        SparseArrayToArray sparseArrayToArray = new SparseArrayToArray(sparseArray);
        //sparseArrayToArray.printArray();
        sparseArrayToArray.transform();
    }
}
