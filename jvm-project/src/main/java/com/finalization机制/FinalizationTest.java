package com.finalization机制;

/**
 * @author eric
 * @date 2022/5/22 15:50
 **/
public class FinalizationTest {
    public static FinalizationTest finalization;//GC roots

    @Override
    protected void finalize() throws Throwable {
        System.out.println("对象复活，只被调用一次");
        finalization = this;
    }

    public static void main(String[] args) throws InterruptedException {
        finalization = new FinalizationTest();

        finalization = null;
        System.gc();
        Thread.sleep(2000);
        if(finalization==null){
            System.out.println("first dead");
        }else{
            System.out.println("first active");
        }

        finalization = null;
        System.gc();
        Thread.sleep(2000);
        if(finalization==null){
            System.out.println("two dead");
        }else{
            System.out.println("two active");
        }

    }
}
