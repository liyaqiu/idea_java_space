package design.designmemanto;

public class GameEntity {
    private String state;
    private String gameName;

    public GameEntity(String state, String gameName) {
        this.state = state;
        this.gameName = gameName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "GameEntity{" +
                "state='" + state + '\'' +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
