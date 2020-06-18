package com.xz.dijkstra;

import java.util.Arrays;

/**
 * @Package: com.xz.dijkstra
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/17 16:45
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = {
                {0,5,7,0,0,0,2},
                {5,0,0,9,0,0,3},
                {7,0,0,0,8,0,0},
                {0,9,0,0,0,4,0},
                {0,0,8,0,0,5,4},
                {0,0,0,4,5,0,6},
                {2,3,0,0,4,6,0}
        };
        //起始顶点
        int startIndex = 6;
        //已访问的的顶点
        int[] alreadyArr = new int[vertex.length];
        //前驱节点
        int[] preVisited = new int[vertex.length];
        //距离长度
        int[] dis = new int[vertex.length];
        //默认6666距离
        Arrays.fill(dis,6666);
        //dis长度，起点为0
        dis[startIndex] = 0;
        //起点默认已访问
        alreadyArr[startIndex] = 1;
        int index = startIndex;
        int len;
        //遍历顶点
        for(int i = 0 ;i < matrix[index].length ; i++){
            //出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
            len = dis[index] + matrix[index][i];
            //该顶点没有访问过 + 距离小于之前的距离 + index顶点到i顶点连通状态
            if(alreadyArr[i]==0 && len < dis[i] && matrix[index][i] != 0){
                //设置前置节点
                preVisited[i] = index;
                //设置距离
                dis[i] = len;
            }
        }
        //遍历除了起点以外的所有顶点
        for(int j = 1;j<vertex.length;j++){
            //筛选出最短距离点，以最短距离点作为起点进行下一次距离计算
            int min = 6666;
            int minIndex = -1;
            for(int i = 0; i < vertex.length ; i ++){
                if(alreadyArr[i] == 0 && dis[i]<min){
                    min = dis[i];
                    minIndex = i;
                }
            }
            alreadyArr[minIndex] = 1;
            for(int i = 0 ;i < matrix[minIndex].length ; i++){
                //出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
                len = dis[minIndex] + matrix[minIndex][i];
                //该顶点没有访问过 + 距离小于之前的距离 + index顶点到i顶点连通状态
                if(alreadyArr[i]==0 && len < dis[i] && matrix[minIndex][i] != 0){
                    //设置前置节点
                    preVisited[i] = minIndex;
                    //设置距离
                    dis[i] = len;
                }
            }
        }
        System.out.println(Arrays.toString(dis));
        System.out.println(Arrays.toString(alreadyArr));
        System.out.println(Arrays.toString(preVisited));
        for (int i = 0; i < dis.length; i++) {
            if(dis[i]!=6666){
                System.out.print(vertex[i]+"("+dis[i]+") ");
            }else{
                System.out.print(vertex[i]+"(N) ");
            }
        }
    }
}
