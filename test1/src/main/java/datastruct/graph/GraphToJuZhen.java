package datastruct.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//图结构-->邻接矩阵（二维数组实现---浪费容量）
public class GraphToJuZhen {
    private int[][] lineage ;
    private List<String> elements;
    private boolean[] isVisit;
    private int lines;

    public GraphToJuZhen(int num) {
        this.lineage = new int[num][num];
        this.elements = new ArrayList<>(num);
        this.isVisit = new boolean[num];
    }

    //添加顶点元素
    public void addElement(String elem){
        elements.add(elem);
    }

    //添加顶点与顶点之间的关系,因为是双向的，所以相互依赖
    public void addLineage(int index1,int index2){
        lineage[index1][index2] = 1;
        lineage[index2][index1] = 1;
        lines++;
    }
    //打印有多少条关系线
    public int printLines(){
        return lines;
    }
    //打印血缘关系
    public void printLineage(){
        System.out.print("  ");
        for (String element : elements) {
            System.out.print(element+" ");
        }
        System.out.println();
        for (int i = 0; i < lineage.length; i++) {
            System.out.print(elements.get(i)+" ");
            for (int j = 0; j < lineage[i].length; j++) {
                System.out.print(lineage[i][j]+" ");
            }
            System.out.println();
        }
    }

    //获取顶点的关系位置
    public int getLineageIndex(int index){
        for (int i = 0; i < lineage.length; i++) {
            if (lineage[index][i]==1) {
                return i;
            }
        }
        return -1;
    }
    //获取顶点从第N个索引开始的位置
    public int getNextLineageIndex(int index1,int index2){
        for (int i = index2+1; i < lineage.length; i++) {
            if (lineage[index1][i]==1) {
                return i;
            }
        }
        return -1;
    }

    //深度打印
    private void depthPrint(int index){
        System.out.print(elements.get(index)+"-->");
        isVisit[index] = true;
        int lineageIndex = getLineageIndex(index);
        while (lineageIndex != -1) {
            if(!isVisit[lineageIndex]){
                depthPrint(lineageIndex);
            }
            lineageIndex = getNextLineageIndex(index, lineageIndex);
        }
    }
    //逐个顶点去遍历，因为是有部分顶点相互不关联倒置无法遍历到
    public void depthPrint(){
        //depthPrint(0);
        for (int i = 0; i < elements.size(); i++) {
            if(!isVisit[i]){
                depthPrint(i);
            }
        }
        isVisit = new boolean[isVisit.length];
    }

    public static void main(String[] args) {
        String[] strs = {"A","B","C","D","E"};
        GraphToJuZhen graph = new GraphToJuZhen(strs.length);
        for (String s : strs) {
            graph.addElement(s);
        }
        graph.addLineage(0, 1);//A-B
        graph.addLineage(0, 2);//A-C
        graph.addLineage(1, 2);//B-C
        graph.addLineage(1, 3);//B-D
        graph.addLineage(1, 4);//B-E

        graph.printLineage();
        graph.depthPrint();
    }
}
