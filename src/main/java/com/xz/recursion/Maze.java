package com.xz.recursion;

/**
 * @Package: com.xz.recursion
 * @ClassName: Maze
 * @Author: xz
 * @Date: 2020/5/21 15:08
 * @Version: 1.0
 */
public class Maze {
    public static void main(String[] args) {

    }
    public static int[][] createMaze(int x,int y){
        if(x < 4 || y < 4){
            throw new RuntimeException("不能低于4");
        }
        int[][] ints = new int[x][y];
        //设置墙

        return ints;
    }
}
