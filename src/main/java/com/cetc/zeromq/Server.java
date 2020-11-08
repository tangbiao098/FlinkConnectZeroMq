package com.cetc.zeromq;


import com.cetc.entity.Student;
import org.zeromq.ZMQ;

import java.util.Random;


public class Server {
    public static void main(String[] args) throws InterruptedException {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket pub = context.socket(ZMQ.PUB);
        pub.bind("tcp://*:7798");
        Random random = new Random();

        while (true) {
            Student stu = new Student("hello_" + random.nextGaussian(), random.nextDouble(), random.nextDouble(), random.nextInt());
            System.out.println("send:" + stu.toString());
            pub.send(stu.toString());
            Thread.sleep(1000L);
        }
    }
}
