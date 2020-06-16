package com.xz.prim;

import java.util.Arrays;

/**
 * @Package: com.xz.prim
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/16 14:00
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        int[][] weight = {
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };
        MinTree minTree = new MinTree();
        Graph graph = minTree.createGraph(verxs, data, weight);
//        minTree.showGraph(graph);
        minTree.prim(graph, 0);
    }
}

class MinTree {
    public Graph createGraph(int verxs, char[] data, int[][] weight) {
        Graph graph = new Graph();
        graph.verxs = verxs;
        graph.data = Arrays.copyOf(data, data.length);
        graph.weight = Arrays.copyOf(weight, weight.length);
        return graph;
    }

    public void showGraph(Graph graph) {
        for (int i = 0; i < graph.verxs; i++) {
            System.out.println(Arrays.toString(graph.weight[i]));
        }
    }

    public void prim(Graph graph, int verxs) {
        int[] visited = new int[graph.verxs];
        visited[verxs] = 1;
        int minWeight = 10000;
        int h1 = -1;
        int h2 = -1;
        for (int i = 1; i < graph.verxs; i++) {
            for (int j = 0; j < graph.verxs; j++) {
                for (int k = 0; k < graph.verxs; k++) {
                    if (visited[j] == 1 && visited[k] == 0 && graph.weight[j][k] < minWeight) {
                        h1 = j;
                        h2 = k;
                        minWeight = graph.weight[j][k];
                    }
                }
            }
            System.out.println(graph.data[h1] + " -> " + graph.data[h2] + " 权重：" + minWeight);
            visited[h2] = 1;
            minWeight = 10000;
        }
    }
}

class Graph {
    int verxs;
    char[] data;
    int[][] weight;
}
