package com.xz.reversepolishnotationcalculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Package: com.xz.reversepolishnotationcalculator
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/5/20 13:39
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        //1+((2+3)*4)-5
        //4 5 * 8 - 60 + 8 2 / +
//        System.out.println(reversePolishNotationCalculator("3 4 + 5 * 6 -"));
        int i = 1+((2+3)*4)-5;
        System.out.println(i);
        String s = infixToSuffix("1+((2+3)*4)-5");
        System.out.println(s);
        System.out.println(i + "是否等于" + reversePolishNotationCalculator(s));
    }

    public static final Map<String, Integer> map = new HashMap<String, Integer>() {{
        put("+", 1);
        put("-", 1);
        put("*", 2);
        put("/", 2);
    }};

    public static String infixToSuffix(String notation) {
        Stack<String> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        for (int i = 0; i < notation.length(); i++) {
            StringBuilder s = new StringBuilder(String.valueOf(notation.charAt(i)));
            if (isNum(s.toString())) {
                while (i + 1 < notation.length() && isNum(String.valueOf(notation.charAt(i + 1)))) {
                    s.append(notation.charAt(++i));
                }
                //数字入栈
                s2.push(s.toString());
            } else if (isBrackets(s.toString())) {
                //括号
                if ("(".equals(s.toString())) {
                    s1.push(s.toString());
                } else {
                    while (true) {
                        String pop = s1.pop();
                        if ("(".equals(pop)) {
                            break;
                        }
                        s2.push(pop);
                    }
                }
            } else {
                //操作符
                if (s1.isEmpty() || "(".equals(s1.peek())) {
                    s1.push(s.toString());
                } else if (map.get(s.toString()) > map.get(s1.peek())) {
                    s1.push(s.toString());
                } else {
                    String pop = s1.pop();
                    s2.push(pop);
                    while (true) {
                        if (s1.isEmpty() || "(".equals(s1.peek())) {
                            s1.push(s.toString());
                            break;
                        }
                        if (map.get(s.toString()) > map.get(s1.peek())) {
                            s1.push(s.toString());
                            break;
                        }
                        String temp = s1.pop();
                        s2.push(temp);
                    }
                }
            }
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        StringBuffer sb = new StringBuffer();
        while (!s2.isEmpty()) {
            sb.insert(0, s2.pop() + " ");
        }
        return sb.toString().trim();
    }

    //3 4 + 5 * 6 -
    //弹3 4 +
    //压入 7
    //弹7 5 *
    //压入 35
    //弹35 6 -
    // 压29
    public static String reversePolishNotationCalculator(String notation) {
        String[] s = notation.trim().split(" ");
        Stack<String> stack = new Stack<>();
        for (String s1 : s) {
            if (isNum(s1)) {
                stack.push(s1);
            } else {
                String num1 = stack.pop();
                String num2 = stack.pop();
                String cal = cal(num1, num2, s1);
                stack.push(cal);
            }
        }
        return stack.pop();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    private static String cal(String num1, String num2, String operation) {
        int i1 = Integer.parseInt(num1);
        int i2 = Integer.parseInt(num2);
        int result;
        switch (operation) {
            case "+":
                result = i1 + i2;
                break;
            case "-":
                result = i2 - i1;
                break;
            case "*":
                result = i1 * i2;
                break;
            case "/":
                result = i2 / i1;
                break;
            default:
                throw new RuntimeException("非法操作符");
        }
        return String.valueOf(result);
    }

    private static boolean isNum(String num) {
        return num.matches("\\d+");
    }

    private static boolean isBrackets(String s) {
        return "(".equals(s) || ")".equals(s);
    }
}

