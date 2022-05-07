package design.designadpater;

public class Client {
    public static void main(String[] args) {
        Adpater adpater = new AnimalAdpater();
        Doctor docker = adpater.getDocker(new Cat());
        docker.kanbing();
    }
}
