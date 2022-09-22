package com.tian.day01;

import java.util.Scanner;

public class 环形数组队列 {
    public static void main(String[] args) {
        //测试环形数组队列
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
        char key=' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):队列取出数据");
            System.out.println("h(head):查看头数据");
            key=scanner.next().charAt(0);//接受一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value=scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.println("取出的数据是"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.println("取出的头数据是"+res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;

            }
        }
        System.out.println("程序退出");
    }
}
//使用数组模拟队列 编写一个ArrayQueue类
class CircleArrayQueue{
    private int maxSize; //表示数组的最大容量
    private int front; //指向队列的第一个元素
    private int rear; //指向队列的最后一个元素的后一个位置
    private int[] arr; //该数据用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArrayQueue(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
        front=0;
        rear=0;


    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return front==rear;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return ;
        }
        //直接将数据加入
        arr[rear]=n;
        //将rear后移 这里必须考虑取模
        rear=(rear+1)%maxSize;
    }
    //获取队列的数据 出队列
    public int getQueue(){
        //判断是否空
        if(isEmpty()){
            //通过抛出异常处理
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        int value=arr[front];
        //2. 将front后移 考虑取模
        front=(front+1)%maxSize;
        //3.返回临时值
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //思路，从front开始 ,遍历多少个元素
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }



    }
    //求出当前队列有效数据的个数
    public int size(){
        //rear=2
        //front=1
        //maxSize=3
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据 注意不是取出数据
    public int headQueue(){
        //判断
        if (isEmpty()){
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }
}