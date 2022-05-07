package datastruct.stack;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Stack stack = new SingleLinkedStack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.showAll();
       stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.showAll();
//        stack.pop();

    }
}
