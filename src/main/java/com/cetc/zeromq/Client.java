package com.cetc.zeromq;

import org.zeromq.ZMQ;

public class Client {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket sub = context.socket(ZMQ.SUB);
        sub.connect("tcp://127.0.0.1:7798");
        sub.subscribe("".getBytes());
        while (true) {
            byte[] recv = sub.recv(0);
            String value = new String(recv);
            System.out.println("rec:" + value);
        }
    }
}
