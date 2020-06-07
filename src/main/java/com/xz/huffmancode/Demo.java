package com.xz.huffmancode;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Package: com.xz.huffmancode
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/7 13:06
 * @Version: 1.0
 */
public class Demo {

    public static void main(String[] args) {
        String str = "i like like like java do you like a javauuuklaldflfsdlf";
        //将字符串构造成节点List
        List<Node> nodes = buildNodeList(str);
        //构造赫夫曼树
        Node node = buildHuffmanTree(nodes);
        //获得赫夫曼编码表
        Map<Byte, String> codes = getHuffmanCodes(node);
        //编码
        byte[] encode = encode(str, codes);
        //解码
        String decode = decode(encode, codes);
        System.out.println(decode);
    }

    private static String decode(byte[] encode, Map<Byte, String> codes) {
        StringBuilder encodeStringBuilder = new StringBuilder();
        for (int i = 0; i < encode.length; i++) {
            int temp = encode[i];
            boolean b = i != encode.length - 1;
            if (b) {
                temp |= 256;
            }
            String s = Integer.toBinaryString(temp);
            if (b) {
                encodeStringBuilder.append(s.substring(s.length() - 8));
            } else {
                encodeStringBuilder.append(s);
            }
        }
        System.out.println(encodeStringBuilder.toString());
        StringBuilder temp = new StringBuilder();
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < encodeStringBuilder.length(); i++) {
            temp.append(encodeStringBuilder.charAt(i));
            if (codes.containsValue(temp.toString())) {
                AtomicReference<Byte> key = new AtomicReference<>((byte) 0);
                codes.forEach((k, v) -> {
                    if (v.equals(temp.toString())) {
                        key.set(k);
                    }
                });
                decode.append(new String(new byte[]{key.get()}));
                temp.delete(0, temp.length());
            }
        }
        return decode.toString();
    }

    private static String fillZero(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        while (stringBuilder.length() < 8) {
            stringBuilder.insert(0, "0");
        }
        return stringBuilder.toString();
    }

    private static byte[] encode(String str, Map<Byte, String> codes) {
        StringBuilder encode = new StringBuilder();
        //编码
        for (byte aByte : str.getBytes()) {
            encode.append(codes.get(aByte));
        }
        System.out.println(encode.toString());
        System.out.println(encode.length());
        //获取字节数组
        int length = (encode.length() + 7) / 8;
        byte[] huffmanBytes = new byte[length];
        for (int i = 0; i < encode.length(); i += 8) {
            String temp;
            if (i + 8 <= encode.length()) {
                temp = encode.substring(i, i + 8);
            } else {
                temp = encode.substring(i);
            }
            huffmanBytes[i / 8] = (byte) Integer.parseInt(temp, 2);
        }
        return huffmanBytes;
    }

    private static Map<Byte, String> getHuffmanCodes(Node node) {
        Map<Byte, String> codes = new HashMap<>();
        getHuffmanCodes(node, "", new StringBuilder(), codes);
        return codes;
    }

    private static List<Node> buildNodeList(String str) {
        Map<Byte, Integer> map = new HashMap<>();
        for (byte aByte : str.getBytes()) {
            if (map.containsKey(aByte)) {
                map.put(aByte, map.get(aByte) + 1);
            } else {
                map.put(aByte, 1);
            }
        }
        List<Node> list = new ArrayList<>();
        map.forEach((k, v) -> {
            Node node = new Node(v, k);
            list.add(node);
        });
        return list;
    }

    private static void getHuffmanCodes(Node node, String code, StringBuilder sb, Map<Byte, String> map) {
        StringBuilder stringBuilder = new StringBuilder(sb);
        stringBuilder.append(code);
        if (node != null) {
            if (node.data == null) {
                getHuffmanCodes(node.left, "0", stringBuilder, map);
                getHuffmanCodes(node.right, "1", stringBuilder, map);
            } else {
                map.put(node.data, stringBuilder.toString());
            }
        }
    }

    private static Node buildHuffmanTree(List<Node> list) {
        while (list.size() != 1) {
            list.sort(Comparator.comparingInt(o -> o.weight));
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.weight + right.weight, null);
            parent.left = left;
            parent.right = right;
            list.add(parent);
            list.remove(left);
            list.remove(right);
        }
        return list.get(0);
    }
}


class Node {
    int weight;
    Byte data;
    Node right;
    Node left;

    public Node(int weight, Byte data) {
        this.weight = weight;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", data=" + data +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }
}
