package design.designobsever;

public class User1 implements User{
    @Override
    public void receive(String msg) {
        System.out.println(msg);
    }
}
