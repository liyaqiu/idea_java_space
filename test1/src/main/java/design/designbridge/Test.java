package design.designbridge;

public class Test {
    public static void main(String[] args) {
//        Shape shape = new CycleShap(new RedColor(), new XiaoMi());
//        shape.drawPicture();

        String s = (new String("aa")+new String("bb")).intern();

        String s1 = "aabb";
        System.out.println(s==s1);
//        System.out.println("----------------");
//        String s2 = new String("aa")+new String("bb").intern();
//        s2.intern();
//        String s3 = "aabb";
//        System.out.println(s2==s3);
    }
}
