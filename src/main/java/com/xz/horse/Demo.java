package com.xz.horse;

import java.util.Arrays;

/**
 * @Package: com.xz.horse
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/18 16:14
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        //创建棋盘
        int[][] chessBoard = new int[8][8];
        //选定起点
        for (int x = 0; x < chessBoard.length; x++) {
            for (int y = 0; y < chessBoard.length; y++) {

            }
        }

        int[][] nextStep = getNextStep(3, 3, chessBoard);
    }

    public static void horse(int x,int y,int count,int[][] chessBoard){
//        if(count!=)
    }

    public static int[][] getNextStep(int x, int y, int[][] chessBoard) {
        int[][] steps = new int[8][2];
        for (int[] step : steps) {
            Arrays.fill(step, -1);
        }
        int index = 0;
        int maxX = chessBoard.length;
        int maxY = chessBoard[0].length;
        if (x - 1 >= 0 && y + 2 < maxY && chessBoard[x - 1][y + 2] == 0) {
            steps[index][0] = x - 1;
            steps[index][1] = y + 2;
            index++;
        }
        if (x + 1 < maxX && y + 2 < maxY && chessBoard[x + 1][y + 2] == 0) {
            steps[index][0] = x + 1;
            steps[index][1] = y + 2;
            index++;
        }
        if (x + 2 < maxX && y + 1 < maxY && chessBoard[x + 2][y + 1] == 0) {
            steps[index][0] = x + 2;
            steps[index][1] = y + 1;
            index++;
        }
        if (x + 2 < maxX && y - 1 >= 0 && chessBoard[x + 2][y - 1] == 0) {
            steps[index][0] = x + 2;
            steps[index][1] = y - 1;
            index++;
        }
        if (x + 1 < maxX && y - 2 >= 0 && chessBoard[x + 1][y - 2] == 0) {
            steps[index][0] = x + 1;
            steps[index][1] = y - 2;
            index++;
        }
        if (x - 1 >= 0 && y - 2 >= 0 && chessBoard[x - 1][y - 2] == 0) {
            steps[index][0] = x - 1;
            steps[index][1] = y - 2;
            index++;
        }
        if (x - 2 >= 0 && y - 1 >= 0 && chessBoard[x - 2][y - 1] == 0) {
            steps[index][0] = x - 2;
            steps[index][1] = y - 1;
            index++;
        }
        if (x - 2 >= 0 && y + 1 < maxY && chessBoard[x - 2][y + 1] == 0) {
            steps[index][0] = x - 2;
            steps[index][1] = y + 1;
        }
        return steps;
    }
}
