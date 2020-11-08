package com.cetc.stream;

import org.apache.flink.streaming.api.functions.source.SocketTextStreamFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeromq.ZMQ;
import java.net.Socket;

public class TcpTextStreamFuntion implements SourceFunction<String> {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(SocketTextStreamFunction.class);

    /**
     * Default delay between successive connection attempts.
     */
    private static final int DEFAULT_CONNECTION_RETRY_SLEEP = 500;

    /**
     * Default connection timeout when connecting to the server socket (infinite).
     */
    private static final int CONNECTION_TIMEOUT_TIME = 0;


    private final String hostname;
    private final int port;

    private volatile boolean isRunning = true;
    private transient Socket currentSocket;

    public TcpTextStreamFuntion(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void run(SourceContext<String> ctx) throws Exception {

        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket sub = context.socket(ZMQ.SUB);
        String path = "tcp://" + hostname + ":" + port;
        sub.connect(path);
        sub.subscribe("".getBytes());

        while (isRunning) {
            byte[] recv = sub.recv(0);
            String value = new String(recv);
            ctx.collect(value);
        }
    }

    public void cancel() {
        isRunning = false;
        Socket theSocket = this.currentSocket;
        if (theSocket != null) {
            IOUtils.closeSocket(theSocket);
        }
    }
}
