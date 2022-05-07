package datastruct.arrayqueue;

public class ArrayQueue {
    private int maxsize;
    private int tail;
    private int head;
    private int[] elements;
    public ArrayQueue(int maxsize) {
        this.maxsize = maxsize+1;
        elements = new int[this.maxsize];
        this.tail = 0;
        this.head = 0;
    }

    private boolean isEmpty(){
        if(tail==head){
            return true;
        }
        return false;
    }

    private boolean isFull(){
        if((tail+1)%maxsize==head){
            return true;
        }
        return false;
    }

    public void add(int element){
        if(isFull()){
            throw new RuntimeException("队列已满");
        }
        elements[tail] = element;
        tail = (tail+1)%maxsize;
    }

    public int remove(){
        if(isEmpty()){
            throw new RuntimeException("没有元素可取");
        }
        int element = elements[head];
        head = (head+1)%maxsize;
        return element;
    }

    public int watchHead(){
        if(isEmpty()){
            throw new RuntimeException("没有元素");
        }
        return elements[head];
    }

    public void showElements(){
        if(isEmpty()){
            throw new RuntimeException("没有元素可查看");
        }
        int size = (tail+maxsize-head)%maxsize+head;
        for (int i = head; i < size; i++) {
            System.out.println(elements[i]);
        }
    }

}
