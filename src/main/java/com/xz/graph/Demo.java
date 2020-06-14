package com.xz.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Package: com.xz.graph
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/12 15:31
 * @Version: 1.0
 */
public class Demo {
    static List<String> list;
    static int[][] edges;
    static int numOfEdges;
    static int size = 8;
    static boolean[] fsed;
    static Queue<String> bfsList;
    static {
        edges = new int[size][size];
        list = new ArrayList<>(size);
        bfsList = new ArrayBlockingQueue<>(size);
    }
    public static void main(String[] args) {
        add("1","2","3","4","5","6","7","8");
        addEdge("1","2",1);
        addEdge("1","3",1);
        addEdge("2","4",1);
        addEdge("2","5",1);
        addEdge("3","6",1);
        addEdge("3","7",1);
        addEdge("4","8",1);
        addEdge("5","8",1);
        addEdge("6","7",1);

        for (int[] edge : edges) {
            for (int i : edge) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }
        dfs();
        System.out.println("----");
        bfs();
    }

    public static void add(String value){
        list.add(value);
    }
    public static void add(String... value){
        list.addAll(Arrays.asList(value));
    }

    public static void addEdge(String i,String j,int weight){
        int i1 = list.indexOf(i);
        int i2 = list.indexOf(j);
        if(i1 == -1 || i2 == -1){
            return;
        }
        edges[i1][i2] = weight;
        edges[i2][i1] = weight;
        numOfEdges++;
    }

    public static void bfs(){
        fsed = new boolean[size];
        for(int i = 0 ;i<list.size();i++){
            if(!fsed[i]){
                bfs(i);
            }
        }
    }

    private static void bfs(int i){
        System.out.println(list.get(i));
        fsed[i] = true;
        bfsList.add(list.get(i));
        while(!bfsList.isEmpty()){
            String s = bfsList.poll();
            int index = list.indexOf(s);
            int firstNeighbor = getFirstNeighbor(s);
            while(firstNeighbor != -1){
                if(!fsed[firstNeighbor]){
                    System.out.println(list.get(firstNeighbor));
                    fsed[firstNeighbor] = true;
                    bfsList.add(list.get(firstNeighbor));
                }
                firstNeighbor = getNextNeighbor(list.get(index),firstNeighbor);
            }
        }
    }

    public static void dfs(){
        fsed = new boolean[size];
        for (int i = 0 ; i < list.size() ; i++){
            if(!fsed[i]){
                dfs(i);
            }
        }
    }

    private static void dfs(int index){
        System.out.println(list.get(index));
        fsed[index] = true;
        int firstNeighbor = getFirstNeighbor(list.get(index));
        while (firstNeighbor != -1){
            if(!fsed[firstNeighbor]){
                dfs(firstNeighbor);
            }
            firstNeighbor = getNextNeighbor(list.get(index),firstNeighbor);
        }

    }

    public static int getFirstNeighbor(String value){
        for(int i = 0; i < list.size(); i++){
            if(edges[list.indexOf(value)][i] == 1){
                return i;
            }
        }
        return -1;
    }

    public static int getNextNeighbor(String value,int index){
        for(int i = index + 1; i < list.size(); i++){
            if(edges[list.indexOf(value)][i] == 1){
                return i;
            }
        }
        return -1;
    }
}
