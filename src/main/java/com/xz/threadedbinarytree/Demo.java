package com.xz.threadedbinarytree;

/**
 * @Package: com.xz.threadedbinarytree
 * @ClassName: Demo
 * @Author: xz
 * @Date: 2020/6/2 14:20
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {
        Node node1 = new Node(1, "张三");
        Node node2 = new Node(2, "李四");
        Node node3 = new Node(3, "王五");
        Node node4 = new Node(4, "小明");
        Node node5 = new Node(5, "小华");
        Node node6 = new Node(6, "小刚");
        Node node7 = new Node(7, "小红");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(node1);
        threadedBinaryTree.threadedNode(node1);
        threadedBinaryTree.threadNode();
    }
}
class ThreadedBinaryTree{
    private Node root;
    private Node pre;

    public void threadNode(){
        Node temp = root;
        while(temp != null){
            while(temp.leftType != 1){
                temp = temp.left;
            }
            System.out.println(temp);
            while(temp.rightType == 1){
                temp = temp.right;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }

    public void threadedNode(Node node){
        if(node == null){
            return;
        }
        //线索化左节点
        threadedNode(node.left);
        //线索化当前节点
        if(node.left == null){
            node.left = pre;
            node.leftType = 1;
        }
        if(pre != null && pre.right == null){
            pre.right = node;
            pre.rightType = 1;
        }
        pre = node;
        //线索化右节点
        threadedNode(node.right);
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder(){
        if(root != null){
            root.preOrder();
        }
    }
    public void midOrder(){
        if(root != null){
            root.midOrder();
        }
    }
    public void postOrder(){
        if(root != null){
            root.postOrder();
        }
    }
    public Node preSearch(int id){
        if(root != null){
            return root.preSearch(id);
        }
        return null;
    }
    public Node midSearch(int id){
        if(root != null){
            return root.midSearch(id);
        }
        return null;
    }
    public Node postSearch(int id){
        if(root != null){
            return root.postSearch(id);
        }
        return null;
    }
    public boolean remove(int id){
        if(root != null){
            return root.remove(id);
        }
        return false;
    }
}
class Node{
    public int id;
    public String name;
    public Node left;
    public Node right;
    //type是1指向树，type是0指向节点
    public int leftType;
    public int rightType;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        //父节点
        System.out.println(this);
        //左子树遍历
        if(left != null){
            left.preOrder();
        }
        //右子树遍历
        if(right != null){
            right.preOrder();
        }
    }

    /**
     * 前序查找
     */
    public Node preSearch(int id){
        if(this.id == id){
            return this;
        }
        if(left != null){
            Node temp = left.preSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(right != null){
            Node temp = right.preSearch(id);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public void midOrder(){
        if(left != null){
            left.midOrder();
        }
        System.out.println(this);
        if(right != null){
            right.midOrder();
        }
    }

    /**
     * 中序查找
     */
    public Node midSearch(int id){
        if(left != null){
            Node temp = left.midSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.id == id){
            return this;
        }
        if(right != null){
            Node temp = right.midSearch(id);
            if(temp != null){
                return temp;
            }
        }
        return null;
    }

    /**
     * 后序遍历
     */
    public void postOrder(){
        if(left != null){
            left.postOrder();
        }
        if(right != null){
            right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 后序查找
     */
    public Node postSearch(int id){
        if(left != null){
            Node temp = left.postSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(right != null){
            Node temp = right.postSearch(id);
            if(temp != null){
                return temp;
            }
        }
        if(this.id == id){
            return this;
        }
        return null;
    }

    /**
     * 删除
     */
    public boolean remove(int id){
        if(this.id == id){
            System.out.println("无法删除根节点");
            return false;
        }
        if(left != null){
            if(left.id == id){
                left = null;
                return true;
            }else{
                boolean remove = left.remove(id);
                if(remove){
                    return true;
                }
            }
        }
        if(right != null){
            if(right.id == id){
                right = null;
                return true;
            }else{
                return right.remove(id);
            }
        }
        return false;
    }
}
