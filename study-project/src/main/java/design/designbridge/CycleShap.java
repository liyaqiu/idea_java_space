package design.designbridge;



public class CycleShap extends Shape {

    public CycleShap(Color color, Supplier supplier) {
        super(color, supplier);
    }

    @Override
    public void drawPicture() {
        System.out.println("这个是圆形，颜色是"+color.getColor()+"厂家是:"+supplier.getSupplier());
    }
}
