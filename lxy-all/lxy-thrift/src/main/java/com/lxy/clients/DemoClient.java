package com.lxy.clients;

import com.lxy.proto.com.lxy.service.DemoSevice;
import com.lxy.serviceimpl.DemoServiceImpl;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TMultiplexedProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;

/**
 * Created by lxy on 2017/2/8.
 */
public class DemoClient {
    public static void main(String[] args){

        String serverType = "simple";


        try{
            TTransport transport;
            if("simple".equals(serverType)){
                transport = new TSocket("localhost", 9090);
                transport = new TFramedTransport(transport, 1024);
                transport.open();
            }
            else {
                TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
                params.setTrustStore("", "thrift", "SunX509", "JKS");
                transport = TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
            }
            TProtocol protocol = new TBinaryProtocol(transport);
//            TMultiplexedProtocol multiplexedProtocol = new TMultiplexedProtocol(protocol, "DemoService");
//            DemoSevice.Client client = new DemoSevice.Client(multiplexedProtocol);
            DemoSevice.Client client = new DemoSevice.Client(protocol);

            perform(client);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void perform(DemoSevice.Client client) throws TException {

        String respose = client.say("Hello World");
        System.out.println(respose);

        long sum = client.add(100, 200);
        System.out.println(sum);
    }
}
