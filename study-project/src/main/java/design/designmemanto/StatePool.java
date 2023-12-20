package design.designmemanto;

import java.util.ArrayList;
import java.util.List;

public class StatePool {

    private List<GameEntity> list = new ArrayList<>();

    public void add(GameEntity gameEntity){
        list.add(gameEntity);
    }

    public GameEntity get(int index){
        return list.get(index);
    }
}
