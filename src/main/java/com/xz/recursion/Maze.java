package com.xz.recursion;

import java.util.Random;

/**
 * @Package: com.xz.recursion
 * @ClassName: Maze
 * @Author: xz
 * @Date: 2020/5/21 15:08
 * @Version: 1.0
 */
public class Maze {
    public static void main(String[] args) {
        int[][] maze = createMaze(12, 17);
        setRandomWall(maze);
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
    }
    public static int[][] createMaze(int x,int y){
        if(x < 4 || y < 4){
            throw new RuntimeException("不能低于4");
        }
        int[][] ints = new int[x][y];
        //设置上下墙
        for(int i = 0 ; i < ints[0].length ; i++){
            ints[0][i] = 1;
            ints[ints.length-1][i] = 1;
        }
        //设置左右墙
        for(int i = 1 ; i< ints.length - 1 ; i++){
            ints[i][0] = 1;
            ints[i][ints[0].length-1] = 1;
        }
        return ints;
    }

    public static void setRandomWall(int[][] arr){
        int x = arr.length;
        int y = arr[0].length;
        Random random = new Random();
        while(true){
            //true为纵墙
            //false为横墙
            boolean xyFlag = random.nextBoolean();
            //true 为左或上
            //false 为右或下
            boolean rlOrTf = random.nextBoolean();
            int length;
            int index;
            if(xyFlag){
                index = random.nextInt(y-4)+2;
                if(rlOrTf){
                    //纵墙在上
                    int i = arr[1][index];
                    if(i!=1){
                        length = random.nextInt(x-3)+1;
                        for(int j = 1 ; j < length +1 ; j++){
                            arr[j][index] = 1;
                        }
                        break;
                    }
                }else{
                    //纵墙在下
                    int i = arr[x-2][index];
                    if(i!=1){
                        length = random.nextInt(x-3)+1;
                        for(int j = x-2 ; j > x-2-length ; j--){
                            arr[j][index] = 1;
                        }
                        break;
                    }
                }
            }else {
                index = random.nextInt(x-4)+2;
                if(rlOrTf){
                    //横墙在左
                    int i = arr[index][1];
                    if(i!=1){
                        length = random.nextInt(y-3)+1;
                        for(int j = 1 ; j < length +1 ; j++){
                            arr[index][j] = 1;
                        }
                        break;
                    }
                }else{
                    //横墙在右
                    int i = arr[index][y-2];
                    if(i!=1){
                        length = random.nextInt(y-3)+1;
                        for(int j = y-2 ; j > y-2-length ; j--){
                            arr[index][j] = 1;
                        }
                        break;
                    }
                }

            }
        }
    }
}
