package design.designmemanto;

public class StateGenertor {
    private String currentState;
    private String currentGameName;

    public void setCurrentState(String currentState,String currentGameName) {
        this.currentState = currentState;
        this.currentGameName = currentGameName;
    }

    public GameEntity saveState(){
        return  new GameEntity(this.currentState, this.currentGameName);
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getCurrentGameName() {
        return currentGameName;
    }

    public void huifu(GameEntity gameEntity) {
        this.currentGameName = gameEntity.getGameName();
        this.currentState = gameEntity.getState();
    }
}
