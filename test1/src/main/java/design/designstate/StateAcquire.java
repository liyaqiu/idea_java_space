package design.designstate;

//开始状态
public class StateAcquire extends AbsState{

    public StateAcquire(PrizePool prizePool) {
        super(prizePool);
    }


    @Override
    public void acquire() {
        if(prizePool.getAmount()>0){
            System.out.println("发放奖品");
            prizePool.setCurrentState(prizePool.getStateStart());
        }else{
            System.out.println("已经没有奖品了");
            prizePool.setCurrentState(prizePool.getStateEnd());
        }
    }
}
