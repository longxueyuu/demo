package javaclass.concurrent;

import java.util.HashMap;
import java.util.Map;


public class Test2 extends Thread{
    static Map<String, String> chm = new HashMap<String, String>();
    public static void main(String[] args) throws InterruptedException {
        chm.put("one", "one");
        new Test2().start();
        Thread.sleep(200);
        chm.put("one", "TTTT");
        System.out.println(chm.get("one") + " -1");
        Thread.sleep(1000);
        System.out.println(chm.get("one") + " -2");
    }
    public void run(){
        try {
            System.out.println("thread2 changing!");
            Thread.sleep(1000);
            chm.put("one", "two");
            System.out.println("thread2 change over");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}