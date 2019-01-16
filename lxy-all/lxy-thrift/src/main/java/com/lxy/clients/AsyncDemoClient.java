package com.lxy.clients;

import com.lxy.proto.com.lxy.service.DemoSevice;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;

/**
 * Created by lxy on 2017/2/8.
 */
public class AsyncDemoClient {
    public static void main(String[] args){

        try{
            TNonblockingTransport transport;
            transport = new TNonblockingSocket("localhost", 9090);

            TProtocol protocol = new TBinaryProtocol(transport);
            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, "DemoService");

            TProtocolFactory tProtocolFactory = new TBinaryProtocol.Factory();

            DemoSevice.AsyncClient asyncClient = new DemoSevice.AsyncClient(new TBinaryProtocol.Factory(), new TAsyncClientManager(), transport);
            perform(asyncClient);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void perform(DemoSevice.AsyncClient asyncClient) throws TException, InterruptedException {

        asyncClient.say("Async Hello World", new SayCallBackHandler());
        // asyncClient.add(100, 200, new AddCallBackHandler());
        Thread.currentThread().join();


        System.out.println("Async client end!");

    }

    public static class SayCallBackHandler implements AsyncMethodCallback<DemoSevice.AsyncClient.say_call> {

        @Override
        public void onComplete(DemoSevice.AsyncClient.say_call response) {
            try {
                System.out.println(response.getResult());
            } catch (TException e) {
            }
        }

        @Override
        public void onError(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static class AddCallBackHandler implements AsyncMethodCallback<DemoSevice.AsyncClient.add_call> {

        @Override
        public void onComplete(DemoSevice.AsyncClient.add_call response) {
            try {
                System.out.println(response.getResult());
            } catch (TException e) {
            }
        }

        @Override
        public void onError(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
