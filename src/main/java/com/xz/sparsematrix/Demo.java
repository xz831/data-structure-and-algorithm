package com.xz.sparsematrix;

import java.io.*;
import java.util.Arrays;

/**
 * @Package: com.xz.sparsematrix
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/16 15:57
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //二维转稀缺
        int[][] arr = {{0,0,1,0},{0,1,0,0},{0,0,0,0},{1,0,0,0}};
        int sum = 0;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                if(anInt != 0){
                    sum++;
                }
            }
        }
        int[][] sparseMatrix = new int[sum+1][3];
        sparseMatrix[0][0] = arr.length;
        sparseMatrix[0][1] = arr[0].length;
        sparseMatrix[0][2] = sum;
        int sparseMatrixIndex = 1;
        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            for (int i1 = 0; i1 < ints.length; i1++) {
                int anInt = ints[i1];
                if(anInt != 0){
                    sparseMatrix[sparseMatrixIndex][0] = i;
                    sparseMatrix[sparseMatrixIndex][1] = i1;
                    sparseMatrix[sparseMatrixIndex][2] = anInt;
                    sparseMatrixIndex++;
                }
            }
        }
        for (int[] matrix : sparseMatrix) {
            System.out.println(Arrays.toString(matrix));
        }
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d://map.data"));
        objectOutputStream.writeObject(sparseMatrix);
        objectOutputStream.flush();
        objectOutputStream.close();
        System.out.println("-----------------------------------");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d://map.data"));
        int[][] mapData = (int[][]) objectInputStream.readObject();
        objectInputStream.close();
        //稀缺转二维
        int[][] arr2 = new int[mapData[0][0]][mapData[0][1]];
        for (int i = 1; i < mapData.length; i++) {
            arr2[mapData[i][0]][mapData[i][1]] = mapData[i][2];
        }
        for (int[] ints : arr2) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
