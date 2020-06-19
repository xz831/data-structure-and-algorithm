package com.xz.horse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Package: com.xz.horse
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/18 16:14
 * @Version: 1.0
 */
public class Demo {

    private static boolean finished = false;
    private static final boolean[] visited = new boolean[8*8];

    public static void main(String[] args) throws InterruptedException {
        //创建棋盘
        int[][] chessBoard = new int[8][8];
        traversal(0,0,1,chessBoard);
        for (int[] ints : chessBoard) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void traversal(int x,int y,int step,int[][] chessBoard) throws InterruptedException {
        //记录第几步
        chessBoard[x][y] = step;
        //设置已访问
        visited[x*chessBoard[0].length+y] = true;
        List<Point> nextStep = getNextStep(x, y, chessBoard);
        for (Point point : nextStep) {
            if(!visited[point.x*chessBoard[0].length+point.y]){
                traversal(point.x,point.y,step+1,chessBoard);
            }
        }
        //棋盘没有走完
        //回溯过程
        if(step < chessBoard.length*chessBoard[0].length && !finished){
            chessBoard[x][y] = 0;
            visited[x*chessBoard[0].length+y] = false;
        }else {
            finished = true;
        }
    }

    public static List<Point> getNextStep(int x, int y, int[][] chessBoard) {
        List<Point> list = new ArrayList<>();
        int maxX = chessBoard.length;
        int maxY = chessBoard[0].length;
        //5
        if (x - 1 >= 0 && y - 2 >= 0 /*&& chessBoard[x - 1][y - 2] == 0*/) {
            list.add(new Point(x - 1,y-2,getNextStepPoint(x - 1,y-2,chessBoard)));
        }
        //6
        if (x - 2 >= 0 && y - 1 >= 0 /*&& chessBoard[x - 2][y - 1] == 0*/) {
            list.add(new Point(x - 2,y-1,getNextStepPoint(x - 2,y-1,chessBoard)));
        }
        //7
        if (x - 2 >= 0 && y + 1 < maxY /*&& chessBoard[x - 2][y + 1] == 0*/) {
            list.add(new Point(x - 2,y+1,getNextStepPoint(x - 2,y+1,chessBoard)));
        }

        //0
        if (x - 1 >= 0 && y + 2 < maxY /*&& chessBoard[x - 1][y + 2] == 0*/) {
            list.add(new Point(x - 1,y+2,getNextStepPoint(x - 1,y+2,chessBoard)));
        }

        //1
        if (x + 1 < maxX && y + 2 < maxY /*&& chessBoard[x + 1][y + 2] == 0*/) {
            list.add(new Point(x + 1,y+2,getNextStepPoint(x + 1,y+2,chessBoard)));
        }

        //2
        if (x + 2 < maxX && y + 1 < maxY /*&& chessBoard[x + 2][y + 1] == 0*/) {
            list.add(new Point(x + 2,y+1,getNextStepPoint(x + 2,y+1,chessBoard)));
        }

        //3
        if (x + 2 < maxX && y - 1 >= 0 /*&& chessBoard[x + 2][y - 1] == 0*/) {
            list.add(new Point(x + 2,y-1,getNextStepPoint(x + 2,y-1,chessBoard)));
        }
        //4
        if (x + 1 < maxX && y - 2 >= 0 /*&& chessBoard[x + 1][y - 2] == 0*/) {
            list.add(new Point(x + 1,y-2,getNextStepPoint(x + 1,y-2,chessBoard)));
        }
        list.sort(Comparator.comparingInt(item->item.score));
        return list;
    }

    public static int getNextStepPoint(int x, int y, int[][] chessBoard) {
        int count = 0;
        int maxX = chessBoard.length;
        int maxY = chessBoard[0].length;
        if (x - 1 >= 0 && y + 2 < maxY /*&& chessBoard[x - 1][y + 2] == 0*/) {
            count++;
        }
        if (x + 1 < maxX && y + 2 < maxY /*&& chessBoard[x + 1][y + 2] == 0*/) {
            count++;
        }
        if (x + 2 < maxX && y + 1 < maxY /*&& chessBoard[x + 2][y + 1] == 0*/) {
            count++;
        }
        if (x + 2 < maxX && y - 1 >= 0 /*&& chessBoard[x + 2][y - 1] == 0*/) {
            count++;
        }
        if (x + 1 < maxX && y - 2 >= 0 /*&& chessBoard[x + 1][y - 2] == 0*/) {
            count++;
        }
        if (x - 1 >= 0 && y - 2 >= 0 /*&& chessBoard[x - 1][y - 2] == 0*/) {
            count++;
        }
        if (x - 2 >= 0 && y - 1 >= 0 /*&& chessBoard[x - 2][y - 1] == 0*/) {
            count++;
        }
        if (x - 2 >= 0 && y + 1 < maxY /*&& chessBoard[x - 2][y + 1] == 0*/) {
            count++;
        }
        return count;
    }
}
class Point{
    int x;
    int y;
    int score;

    public Point(int x, int y,int score) {
        this.x = x;
        this.y = y;
        this.score = score;
    }
}
