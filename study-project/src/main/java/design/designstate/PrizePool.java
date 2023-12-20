package design.designstate;

public class PrizePool {
    State currentState;
    int amount = 1;
    State stateStart = new StateStart(this);
    State stateSubstract = new StateSubstract(this);
    State stateAcquire = new StateAcquire(this);
    State stateEnd = new StateEnd(this);

    {
        currentState = getStateStart();
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void subtractAmount() {
        System.out.println("准备抽奖");
        currentState.subtractAmount();
    }


    public void randomAcquire() {
        if(currentState.randomAcquire()){
            currentState.acquire();
        }
    }

    public State getStateStart() {
        return stateStart;
    }

    public State getStateSubstract() {
        return stateSubstract;
    }

    public State getStateEnd() {
        return stateEnd;
    }

    public State getStateAcquire() {
        return stateAcquire;
    }

    public int getAmount() {
        int a = amount--;
        return a;
    }
}
