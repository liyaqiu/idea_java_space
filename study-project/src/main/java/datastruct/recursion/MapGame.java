package datastruct.recursion;

public class MapGame {
    int map[][]  = new int[10][10];

    {
        for (int i = 0; i <map.length ; i++) {
            map[0][i]=1;
            map[9][i]=1;
        }
        for (int i = 0; i <map.length ; i++) {
            map[i][0]=1;
            map[i][9]=1;
        }
        for (int i = 0; i <map.length ; i++) {
            if(i == 1){
                continue;
            }
            map[5][i] = 1;
        }
        map[8][2] = 1;

    }
    private void print(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
    }
    public static void main(String[] args) {
//        MapGame game = new MapGame();
//        game.print();
//        game.run(1,1);
//        game.print();
    }



    private boolean run(int row, int cell) {
        //map[8][8] = 6;
        if(map[8][8] == 2){
            return true;
        }
        if(map[row][cell] == 0){
            map[row][cell] = 2;
            if(run(row+1,cell)){
                return true;
            }
            if(run(row,cell+1)){
                return true;
            }
            if(run(row-1,cell)){
                return true;
            }
            if(run(row,cell-1)){
                return true;
            }
            map[row][cell] = 3;
            return false;
        }
        return false;
    }
}
