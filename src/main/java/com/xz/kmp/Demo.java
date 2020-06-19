package com.xz.kmp;

import java.util.Arrays;

/**
 * @Package: com.xz.kmp
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/14 16:50
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
//        String str1="你好jav a你好 j ava 你好java好java你";
//        String str2="好java你";
//        int i = violenceMatch(str1, str2);
//        System.out.println(i);
//        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "ABCDABD";
//        System.out.println(kmp(str1, str2));
        System.out.println(Arrays.toString(kmpTable2("ABCDABA")));
        System.out.println(Arrays.toString(kmpTable("ABCDABA")));
    }

    public static int kmp(String s1, String s2) {
        String max = s1.length() > s2.length() ? s1 : s2;
        String min = s1.length() <= s2.length() ? s1 : s2;
        int[] ints = kmpTable2(min);
        for (int j = 0, i = 0; j < min.length() && i < max.length(); ) {
            if (max.charAt(i) == min.charAt(j)) {
                if (j == min.length() - 1) {
                    return i - j;
                }
                j++;
                i++;
            } else {
                i = i - (j - ints[j]) + 1;
                j = 0;
            }
        }
        return -1;
    }

    public static int[] kmpTable2(String str) {
        int[] table = new int[str.length()];
        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = table[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            table[i] = j;
        }
        return table;
    }

    /**
     * 部分匹配表
     *
     * @param str
     * @return
     */
    public static int[] kmpTable(String str) {
        int[] kmpTable = new int[str.length()];
        for (int i = 1; i < kmpTable.length; i++) {
            String substring = str.substring(0, i + 1);
            String[] prefix = prefix(substring);
            String[] suffix = suffix(substring);
            kmpTable[i] = getMaxLength(prefix, suffix);
        }
        return kmpTable;
    }

    public static int getMaxLength(String[] str1, String[] str2) {
        int max = 0;
        for (int i = 0; i < str1.length; i++) {
            if (str1[i].equals(str2[i])) {
                max = str1[i].length();
            }
        }
        return max;
    }

    public static String[] prefix(String str) {
        String[] prefix = new String[str.length() - 1];
        for (int i = 1; i < str.length(); i++) {
            prefix[i - 1] = str.substring(0, i);
        }
        return prefix;
    }

    public static String[] suffix(String str) {
        String[] suffix = new String[str.length() - 1];
        for (int i = 1; i < str.length(); i++) {
            suffix[i - 1] = str.substring(str.length() - i);
        }
        return suffix;
    }

    /**
     * 暴力匹配
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int violenceMatch(String s1, String s2) {
        String max = s1.length() > s2.length() ? s1 : s2;
        String min = s1.length() <= s2.length() ? s1 : s2;
        for (int j = 0, i = 0; j < min.length() && i < max.length(); ) {
            if (max.charAt(i) == min.charAt(j)) {
                if (j == min.length() - 1) {
                    return i - j;
                }
                j++;
                i++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return -1;
    }
}
