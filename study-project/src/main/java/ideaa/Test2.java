package ideaa;

import org.junit.Test;

/**
 * @author lyq
 * @date 2021/7/5 23:46
 */

public class Test2 {

    public static void main(String[] args) {

        new Test2().test01();
    }

    public void test01() {
        System.out.println("Test2.test01");
    }

    public void test02() {
        System.out.println("Test2.test02");
    }

    @Test
    public void test03() {
        System.out.println("Test2.test03");
        String str = "nihao";

    }

}
