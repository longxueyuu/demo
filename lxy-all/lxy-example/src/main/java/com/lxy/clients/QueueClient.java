package com.lxy.clients;

import com.lxy.queue.MyBlockingQueue;

/**
 * Created by lxy on 2017/4/27.
 */
public class QueueClient {
    public static void main(String[] args){

        MyBlockingQueue bq = new MyBlockingQueue(10);

        Thread producer = new Thread(){

            @Override
            public void run(){
                Integer i = 0;
                while(true){
                    try {
                        bq.offer(i);
//                        double mis = 150 * Math.random();
//                        Thread.sleep((int)mis);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        };


        Thread consumer = new Thread(){

            @Override
            public void run(){
                while(true){
                    try {
                        int i = (Integer) bq.take();
                        System.out.println(i);
//                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        producer.start();
        consumer.start();

    }
}
