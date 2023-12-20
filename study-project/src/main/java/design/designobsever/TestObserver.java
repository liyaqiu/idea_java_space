package design.designobsever;

import java.util.Observable;
import java.util.Observer;

public class TestObserver {
    public static void main(String[] args) {
        Topic2 topic = new Topic2();
        User3 user3 = new User3();
        topic.addObserver(user3);
        topic.addObserver(new User4());
        topic.change();
        topic.notifyObservers(user3);
    }

}
class Topic2 extends Observable{
    void change(){
        super.setChanged();
    }
}


class User3 implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("我3被调用了"+arg.getClass()+o.getClass());
    }
}

class User4 implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("我4被调用了");
    }
}