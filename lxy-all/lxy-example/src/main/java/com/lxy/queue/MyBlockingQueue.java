package com.lxy.queue;

/**
 * Created by lxy on 2017/4/27.
 */
public class MyBlockingQueue {
    private Object[] queue;
    private int size;
    private int head = -1, tail = -1;

    public MyBlockingQueue(int size)
    {
        this.size = size;
        queue = new Object[size];
    }

    public void offer(Object obj) throws InterruptedException {
        synchronized (queue){
            while((tail + 1) % size == head)
            {
                System.out.println("producer wait...");
                queue.wait();
            }
            tail = (tail + 1) % size;
            queue[tail] = obj;
            queue.notify();
        }
    }

    public Object take() throws InterruptedException {
        Object obj = null;
        synchronized (queue){
            while(head == tail)
            {
                System.out.println("consumer wait...");
                queue.wait();
            }
            head = (head + 1) % size;
            obj = queue[head];
            queue.notify();
        }
        return obj;
    }
}
