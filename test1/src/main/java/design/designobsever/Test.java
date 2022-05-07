package design.designobsever;

public class Test {
    public static void main(String[] args) {
        ITopic topic = new Topic();
        User1 user1 = new User1();
        User2 user2 = new User2();
        topic.subscribe(user1);
        topic.subscribe(user2);
        topic.sends();


    }
}
