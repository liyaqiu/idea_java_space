package datastruct.stack;

import javax.naming.OperationNotSupportedException;

public interface Stack<E> {
    void push(E i);
    E pop();
    default boolean isFull() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    boolean isEmpty();
    void showAll();
    E peek();
}
