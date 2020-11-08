package com.cetc.zeromq;

import com.cetc.stream.TcpTextStreamFuntion;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class FlinkConnect {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.addSource(new TcpTextStreamFuntion("127.0.0.1",7798)).print();
        env.execute("hjj");
    }
}
