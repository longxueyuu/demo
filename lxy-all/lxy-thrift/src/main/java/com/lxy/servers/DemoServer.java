package com.lxy.servers;

import com.lxy.proto.com.lxy.service.DemoSevice;
import com.lxy.serviceimpl.DemoServiceAsyncImpl;
import com.lxy.serviceimpl.DemoServiceImpl;
import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

/**
 * Created by lxy on 2017/2/8.
 */
public class DemoServer {
    public static DemoServiceImpl demoService;

    public static DemoServiceAsyncImpl asyncDemoService;

    public static DemoSevice.Processor processor;

    public static DemoSevice.AsyncProcessor asyncProcessor;

    public static void main(String args[]){

        try {
            demoService = new DemoServiceImpl();
            asyncDemoService = new DemoServiceAsyncImpl();
            processor = new DemoSevice.Processor<>(demoService);
            asyncProcessor = new DemoSevice.AsyncProcessor<>(asyncDemoService);
//            TMultiplexedProcessor multiplexedProcessor = new TMultiplexedProcessor();
//            multiplexedProcessor.registerProcessor("DemoService", processor);

            Runnable simple = new Runnable() {
                @Override
                public void run() {
                    simple(asyncProcessor);
                }
            };

            Runnable secure = new Runnable() {
                @Override
                public void run() {
                    // secure(processor);
                }
            };

            new Thread(simple).start();
            // new Thread(secure).start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void simple(TProcessor procesor){
        try {
            // TServerTransport serverTransport = new TServerSocket(9090);
            TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9090);

            // TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(procesor));
            TServer server = new TNonblockingServer(new TNonblockingServer.Args(serverTransport).processor(procesor));

            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(procesor));

            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void secure(DemoSevice.Processor processor) {
        TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
        params.setKeyStore("", "thrift", null, null);
        try {
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, params);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the secure server...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }
}
