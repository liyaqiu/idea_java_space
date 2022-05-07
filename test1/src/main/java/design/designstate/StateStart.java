package design.designstate;

//开始状态
public class StateStart extends AbsState{

    public StateStart(PrizePool prizePool) {
        super(prizePool);
    }

    //减去金额
    @Override
    public void subtractAmount() {
        System.out.println("减去资金");
        prizePool.setCurrentState(prizePool.getStateSubstract());
    }
}
