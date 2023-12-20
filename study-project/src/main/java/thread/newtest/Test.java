package thread.newtest;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
        Test1111 test1111 = new Test1111();
        Test1111 deepClone = test1111.deepClone();
        Test1111 deepClone1 = test1111.deepClone();
        System.out.println(deepClone.haha.hashCode());
        System.out.println(deepClone1.serialVersionUID);
        System.out.println(deepClone1.haha.hashCode());

    }


}
