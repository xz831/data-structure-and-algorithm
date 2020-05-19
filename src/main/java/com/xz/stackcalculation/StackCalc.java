package com.xz.stackcalculation;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Package: com.xz.stackcalculation
 * @ClassName: StackCalc
 * @Author: xz
 * @Date: 2020/5/19 22:10
 * @Version: 1.0
 */
public class StackCalc {
    // 计算符号的权重
    private static Map<String, Integer> OP_WEIGHT = new HashMap<>();

    /**
     * 设置权重，只考虑加减乘除
     */
    static {
        OP_WEIGHT.put("+", 0);
        OP_WEIGHT.put("-", 0);
        OP_WEIGHT.put("*", 1);
        OP_WEIGHT.put("/", 1);
    }

    /**
     * 多位缺陷
     * @param args
     */
    public static void main(String[] args) {
        // 从简单开始找寻规律
        // 1+2*3
        // 应该转换为
        // 123*+
        String str = "2-2*2-3";
        StringBuilder result = new StringBuilder();
        char[] arr = str.toCharArray();
        Stack<String> ops = new Stack<>();
        for (char c : arr) {
            String s = String.valueOf(c);
            // 逐个入栈，如果是操作符
            if (OP_WEIGHT.containsKey(s)) {
                // 如果为空直接入栈
                if (ops.empty()) {
                    ops.push(s);
                }else{
                    // 判断和栈顶的符号的优先级
                    // 如果小于等于栈顶的符号，说明碰到了同级的操作符，先进的要排在前面，栈应该排空，从后往前排空
                    // 如果大于，说明后来的符号符号应该先执行，那么这么符号应该排在前面，暂时排在栈顶，输出时先输出
                    // 为了和下一个操作数比较，不管如何，都应该把操作符继续压入栈
                    if(OP_WEIGHT.get(s) <= OP_WEIGHT.get(ops.peek())){
                        while(ops.size() > 0){
                            result.append(ops.pop());
                        }
                    }
                    ops.push(s);
                }
            } else {
                // 数组则直接打印
                result.append(s);
            }
        }
        // 循环完毕，将op清空
        while(ops.size() > 0){
            result.append(ops.pop());
        }
        // 验证结果
        System.out.println(result);

        /** 执行语句的逻辑 */
        // 轮询，如果碰到操作符，则将最近的两个数据根据操作符计算出结果，然后作为结果入栈，一直递归下去
        // 其实是个递归，一直到栈中只剩一个元素
        // 因为我们有压栈的过程，并且只可能最后的两个数进行计算，所以不用写成递归
        Stack<String> nums = new Stack<>();
        arr = result.toString().toCharArray();
        for(char c : arr){
            String s = String.valueOf(c);
            if (OP_WEIGHT.containsKey(s)) {
                // 如果是符号，将栈顶的两个数进行计算，将结果入栈
                double res = calc(s, Double.valueOf(nums.pop()), Double.valueOf(nums.pop()));
                // 将这个结果放入栈顶
                nums.push(String.valueOf(res));
            }else{
                // 如果是数字直接入栈
                nums.push(s);
            }
        }
        // 最终会剩下一个数值，计算结束，打印结果
        System.out.println(String.format("结果为%s", nums.peek()));
    }

    /**
     * 根据操作符和操作数，执行运算逻辑
     * 注意值的先后，先pop出来的值是被加/减/乘/除数
     * @param op
     * @param n2
     * @param n1
     * @return
     */
    private static double calc(String op, Double n2, Double n1){
        double result = 0;
        switch (op){
            case "+":
                result = n1.doubleValue() + n2.doubleValue();
                break;
            case "-":
                result = n1.doubleValue() - n2.doubleValue();
                break;
            case "*":
                result = n1.doubleValue() * n2.doubleValue();
                break;
            case "/":
                result = n1.doubleValue() / n2.doubleValue();
                break;
        }
        return result;
    }
}
