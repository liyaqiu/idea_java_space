package datastruct.sort;

public interface Sort {
    void main(int[] array);
    default void exec(int[] array,String sortName){
        long startTime= 0L;
        long endTime = 0L;

        startTime = System.currentTimeMillis();
        main(array);
        endTime = System.currentTimeMillis();
        System.out.println(sortName+":"+((endTime-startTime)));

    }
}
