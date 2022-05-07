package datastruct.stack;

public class ArrayStack implements Stack<Double>{

    private int maxsize;
    private Double[] elements;
    private int top = -1;

    public ArrayStack(int maxsize) {
        this.maxsize = maxsize;
        elements = new Double[maxsize];
    }

    @Override
    public void push(Double i) {
        if(isFull()){
            System.out.println("栈满");
        }
        top++;
        elements[top] = i;
    }

    @Override
    public Double pop() {
        if(isEmpty()){
            throw new RuntimeException("栈空");
        }
        double i = elements[top];
        top--;
        return i;
    }

    @Override
    public boolean isFull() {
        if(top==maxsize-1){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(top==-1){
            return true;
        }
        return false;
    }

    @Override
    public void showAll() {
        if(isEmpty()){
            return;
        }
        for (int i = top; i > -1 ; i--) {
            System.out.printf("elements[%d]=%d\n",i,elements[i]);
        }
        System.out.println("--------------");
    }

    @Override
    public Double peek() {
        if(isEmpty()){
            throw  new RuntimeException("没有数据");
        }
        return elements[top];
    }
}
