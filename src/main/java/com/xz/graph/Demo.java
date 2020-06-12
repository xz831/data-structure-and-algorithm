package com.xz.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    static int size = 5;
    static {
        edges = new int[size][size];
        list = new ArrayList<>(size);
    }
    public static void main(String[] args) {
        add("A","B","C","D","E");
        addEdge("A","B",1);
        addEdge("A","C",1);
        addEdge("B","C",1);
        addEdge("B","D",1);
        addEdge("B","E",1);
        for (int[] edge : edges) {
            for (int i : edge) {
                System.out.print(i+"\t");
            }
            System.out.println();
        }
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
}
