package javaclass.concurrent.blockingqueue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestQueue {
	
	private Queue<Integer> queue = new PriorityQueue<Integer>(10);

	public static void main(String[] args) {

		
		TestQueue bread = new TestQueue();
		ExecutorService pool = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 10; i++)
		{
			pool.execute(new Producer(bread));
			pool.execute(new Consumer(bread));
		}
		pool.shutdown();
		
	}
	
	public void produce()
	{
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized(queue){
				while(queue.size() == 10)
				{
					try {
						queue.wait();
					} catch (InterruptedException e) {
						queue.notify();
					}
				}
				queue.offer(1);
				queue.notify();
				System.out.println("produce: queue.size: " + queue.size());
			}
		}
	}
	
	
	public void consume()
	{
		while(true)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized(queue){
				while(queue.size() == 0)
				{
					try {
						queue.wait();
					} catch (InterruptedException e) {
						queue.notify();
					}
				}
				queue.poll();
				queue.notify();
				System.out.println("consume: queue.size: " + queue.size());
			}
		}
	}
}



class Producer implements Runnable{

	private TestQueue bread;
	
	Producer(TestQueue bread)
	{
		this.bread = bread;
	}

	@Override
	public void run() {
	
		bread.produce();
		
	}
}



class Consumer implements Runnable{

	private TestQueue bread;
	
	Consumer(TestQueue bread)
	{
		this.bread = bread;
	}

	@Override
	public void run() {
		
		bread.consume();
		
	}
	
}














