package design.designtemplete;

public abstract class Milk implements IMilk {

    public final void operate(){

        operate1();
        operate2();
        operate3();
    }

    protected abstract void operate1();
    protected abstract void operate2();
    protected void operate3(){
        System.out.println("操作3");
    }


}
