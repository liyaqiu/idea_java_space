package design.designstate;


public interface State {
    //减去资金
    void subtractAmount();
    //抽奖
    boolean randomAcquire();
    //领奖
    void acquire();
}

abstract class AbsState implements State{
    PrizePool prizePool;

    public AbsState(PrizePool prizePool) {
        this.prizePool = prizePool;
    }

    @Override
    public boolean randomAcquire() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void subtractAmount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void acquire() {
        throw new UnsupportedOperationException();
    }
}
