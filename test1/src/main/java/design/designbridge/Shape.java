package design.designbridge;

public abstract class Shape {
    protected Color color;
    protected Supplier supplier;

    public Shape(Color color, Supplier supplier) {
        this.color = color;
        this.supplier = supplier;
    }

    protected abstract void drawPicture();

}
