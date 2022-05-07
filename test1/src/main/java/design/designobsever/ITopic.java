package design.designobsever;

public interface ITopic {
    void sends();
    void remove(User user);

    void subscribe(User user);
}
