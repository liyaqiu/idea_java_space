package design.designstate;

import java.util.Random;

//开始状态
public class StateSubstract extends AbsState{

    public StateSubstract(PrizePool prizePool) {
        super(prizePool);
    }

    @Override
    public boolean randomAcquire() {
        System.out.println("正在抽奖");
        if(new Random().nextInt(10)>5){
            prizePool.setCurrentState(prizePool.getStateAcquire());
            System.out.println("抽中奖品");
            return true;
        }
        prizePool.setCurrentState(prizePool.getStateStart());
        System.out.println("没抽中奖品");
        return false;
    }
}
