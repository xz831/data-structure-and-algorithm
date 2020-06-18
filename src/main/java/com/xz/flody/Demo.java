package com.xz.flody;

import java.util.Arrays;

/**
 * @Package: com.xz.flody
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/18 14:49
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int n = 65535;
        int[][] matrix = {
                {0, 5, 7, n, n, n, 2},
                {5, 0, n, 9, n, n, 3},
                {7, n, 0, n, 8, n, n},
                {n, 9, n, 0, n, 4, n},
                {n, n, 8, n, 0, 5, 4},
                {n, n, n, 4, 5, 0, 6},
                {2, 3, n, n, 4, 6, 0}
        };
        int[][] dis = Arrays.copyOf(matrix, matrix.length);
        int[][] pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
        int len;
        //中间节点
        for (int i = 0; i < vertex.length; i++) {
            //开始节点
            for (int j = 0; j < vertex.length; j++) {
                //结束节点
                for (int k = 0; k < vertex.length; k++) {
                    len = dis[j][i] + dis[i][k];
                    if(len < dis[j][k]){
                        dis[j][k] = len;
                        pre[j][k] = pre[i][k];
                    }
                }
            }
        }
        printDis(dis,vertex);
    }

    public static void printDis(int[][] dis, char[] vertex) {
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis[i].length; j++) {
                String weight = (i == j) ? "0" : dis[i][j] == 66535 ? "N" : String.valueOf(dis[i][j]);
                System.out.print("<" + vertex[i] + "," + vertex[j] + ">="+ weight+" ");
            }
            System.out.println();
        }
    }
}
