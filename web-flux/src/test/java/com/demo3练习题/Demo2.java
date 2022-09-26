package com.demo3练习题;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author eric
 * @date 2022/9/26 1:38
 **/
public class Demo2 {
    @Test
    public void test(){
        /*Bank bank = new Bank();
        AtomicBoolean bl = new AtomicBoolean(true);
        while (bl.get()){
            Consumer<Integer> consumer = (money) -> {
                if (money == 0) {
                    bl.set(false);
                } else {
                    System.out.println("取出了" + money + "多少钱！！！");
                }
            };
            bank.takeMoney(consumer);
        }*/

        Consumer<Person3> c1 = (x) ->{
            System.out.println("x:"+x.getCount()+"  "+this.hashCode());
            x.setCount(800);
        };
        //c1.accept(new Person3());

        c1.andThen((y) -> {
            System.out.println("y:"+y.getCount()+"  "+this.hashCode());
            y.setCount(500);
        }).andThen((z) -> {
            System.out.println("z:"+z.getCount()+"  "+this.hashCode());

        }).accept(new Person3());

    }
    
}



class Person3{
    int count =100;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


class Bank{
    Integer money =10000;
    public void takeMoney(Consumer<Integer> consumer){
        if(this.money>0){
            this.money -=1000;
            consumer.accept(1000);
        }else{
            consumer.accept(0);
        }
    }
}