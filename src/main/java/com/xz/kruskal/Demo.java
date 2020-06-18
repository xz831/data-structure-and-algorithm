package com.xz.kruskal;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Package: com.xz.kruskal
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/17 10:08
 * @Version: 1.0
 */
public class Demo {
    static char[] vertex = {'A','B','C','D','E','F','G'};
    static int[][] matrix = {
            {0,12,-1,-1,-1,16,14},
            {12,0,10,-1,-1,7,-1},
            {-1,10,0,3,5,6,-1},
            {-1,-1,3,0,4,-1,-1},
            {-1,-1,5,4,0,2,8},
            {16,7,6,-1,2,0,9},
            {14,-1,-1,-1,8,9,0}
    };
    static int edgeNum;
    static {
        int count = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                if(anInt != 0 && anInt != -1){
                    count++;
                }
            }
        }
        edgeNum = count/2;
    }
    public static void main(String[] args) {
        EData[] edata = getEdata();
        Arrays.sort(edata, Comparator.comparingInt(item->item.weight));
        int[] ends = new int[edgeNum];
        EData[] rets = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < edata.length; i++) {
            int p1 = getPosition(edata[i].start);
            int p2 = getPosition(edata[i].end);
            int ends1 = getEnds(ends, p1);
            int ends2 = getEnds(ends, p2);
            if(ends1 != ends2){
                ends[ends1] = ends2;
                rets[index++] = edata[i];
            }
        }
        System.out.println(Arrays.toString(rets));
    }

    public static int getEnds(int[] ends,int i){
        while(ends[i] != 0 ){
            i = ends[i];
        }
        return i;
    }

    public static int getPosition(char ch){
        for (int i = 0; i < vertex.length; i++) {
            if(vertex[i] == ch){
                return i;
            }
        }
        return -1;
    }

    public static EData[] getEdata(){
        EData[] result = new EData[edgeNum];
        int index = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int i1 = 0; i1 < matrix[i].length; i1++) {
                if(matrix[i][i1] != 0 && matrix[i][i1] != -1 && enableEdge(result,vertex[i],vertex[i1])){
                    result[index++] = new EData(vertex[i],vertex[i1],matrix[i][i1]);
                }
            }
        }
        return result;
    }

    public static boolean enableEdge(EData[] eData,char s,char e){
        for (EData eDatum : eData) {
            if(eDatum != null &&((eDatum.start==s&&eDatum.end==e)||(eDatum.end==s&&eDatum.start==e))){
                return false;
            }
        }
        return true;
    }
}
class EData{
    //起点
   char start;
   //终点
   char end;
   //权值
   int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}
