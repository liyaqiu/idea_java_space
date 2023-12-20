package design.designobsever;

public class User2 implements User{
    @Override
    public void receive(String msg) {
        System.out.println(msg);
    }
}
