package design.designmemanto;

public class Test {
    public static void main(String[] args) {
        StateGenertor stateGenertor  = new StateGenertor();
        StatePool statePool = new StatePool();

        stateGenertor.setCurrentState("1", "剑侠世界");
        statePool.add(stateGenertor.saveState());
        stateGenertor.setCurrentState("2", "剑侠世界");
        statePool.add(stateGenertor.saveState());
        stateGenertor.setCurrentState("3", "剑侠世界");
        statePool.add(stateGenertor.saveState());

        System.out.println(stateGenertor.getCurrentState()+stateGenertor.getCurrentGameName());
        stateGenertor.huifu(statePool.get(0));

        System.out.println(stateGenertor.getCurrentState()+stateGenertor.getCurrentGameName());

    }
}
