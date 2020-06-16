package com.xz.greedy;

import java.util.*;

/**
 * @Package: com.xz.greedy
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/15 14:52
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) throws Throwable {
        //贪心算法
        String[] station = {"K1","K2","K3","K4","K5"};
//        String[][] area = {{"北京","上海","天津"},{"广州","北京","深圳"},{"成都","上海","杭州"},{"上海","天津"},{"杭州","大连"}};
        String[][] area = {{"北京","上海","广州"},{"北京","杭州","天津"},{"上海","浙江","重庆"},{"广州","湖南"},{"深圳","大连"}};
        Set<String> overridden = new HashSet<>();
        List<String> chooseStation = new ArrayList<>();
        long count = Arrays.stream(area).flatMap(Arrays::stream).distinct().count();
        while(overridden.size() != count){
            List<Integer> pointList = new ArrayList<>();
            for (int i = 0; i < station.length; i++) {
                //不包括这个站台
                if(!chooseStation.contains(station[i])){
                    //评分
                    int point = 0;
                    for (int i1 = 0; i1 < area[i].length; i1++) {
                        if(!overridden.contains(area[i][i1])){
                            point++;
                        }
                    }
                    pointList.add(point);
                }else{
                    pointList.add(0);
                }
            }
            OptionalInt max = pointList.stream().mapToInt(item -> item).max();
            int i = pointList.indexOf(max.orElseThrow(Throwable::new));
            chooseStation.add(station[i]);
            overridden.addAll(Arrays.asList(area[i]));
        }
        System.out.println(chooseStation);
    }
}
