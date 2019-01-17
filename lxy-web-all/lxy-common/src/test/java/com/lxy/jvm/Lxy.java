package com.lxy.jvm;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by lxy on 08/12/2017.
 */
public class Lxy implements LxyService {

    private static LxyService lxyServiceProxy;

    private static Lxy lxyCglibProxy;

    private static Lxy lxyCglibProxy2;

    private static Lxy lxyCglibProxy3;

    private static HashSet<LxyService> set = new HashSet<>();

    @Override
    public void say(String name) {
        System.out.println(name);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LxyService lxyService = new Lxy();

//        lxyServiceProxy = new LxyProxy(lxyService).getProxy();
//        lxyServiceProxy.sing("java proxy");
        System.out.println("-------------");

//        LxyProxy tempLxyProxy = new LxyProxy(lxyService);
//
//        LxyCglibProxy tempLxyCglibProxy = new LxyCglibProxy(lxyService);
//        for(;;) {
//            set.add(tempLxyProxy.getProxy());
//            set.add(tempLxyCglibProxy.getCglibProxy());
//            Thread.sleep(100);
//        }


        lxyCglibProxy = new LxyCglibProxy(lxyService).getCglibProxy();
        lxyCglibProxy.sing("cglib proxy");
//
//        lxyCglibProxy2 = new LxyCglibProxy(lxyService).getCglibProxy();
//        lxyCglibProxy2.sing("spotlight");
//
//        lxyCglibProxy3 = new LxyCglibProxy(lxyService).getCglibProxy();
//        lxyCglibProxy3.sing("spotlight");
//
//
//        ServerSocketChannel serverSocket = ServerSocketChannel.open();
//        serverSocket.bind(new InetSocketAddress(9099));
//        serverSocket.accept();
    }

    @Override
    public void sing(String song) {

        System.out.println(song);
        say("invoke in sing!");
    }

}
