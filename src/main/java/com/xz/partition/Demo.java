package com.xz.partition;

/**
 * @Package: com.xz.partition
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/14 11:52
 * @Version: 1.0
 */
public class Demo {
    static int count = 0;
    public static void main(String[] args) {
        hanoiTower(3,"A","B","C");
        System.out.println(count);
    }

    public static void hanoiTower(int num,String a,String b,String c){
        count++;
        if(num == 1){
            System.out.println("第1个盘 " + a +" -> " + c);
        }else{
            //所有的盘A->B
            hanoiTower(num-1,a,c,b);
            //最下面的盘A->C
            System.out.println("第"+num+"个盘 " + a +" -> " + c);
            //所有的B->C
            hanoiTower(num-1,b,a,c);
        }
    }
}
