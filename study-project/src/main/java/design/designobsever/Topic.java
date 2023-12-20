package design.designobsever;

import java.util.ArrayList;
import java.util.List;

public class Topic implements ITopic{

    List<User> users = new ArrayList<>();

    @Override
    public void sends() {
        for (User user : users) {
            user.receive("最新新闻发布");
        }
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public void subscribe(User user) {
        users.add(user);
    }
}
