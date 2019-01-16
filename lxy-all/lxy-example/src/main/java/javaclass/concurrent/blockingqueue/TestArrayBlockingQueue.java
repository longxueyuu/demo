package javaclass.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestArrayBlockingQueue {
	
	private BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(20);
	
	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		TestArrayBlockingQueue food = new TestArrayBlockingQueue();
		for(int i =0; i < 20; i++)
		{
			pool.execute(new Provider(food));
			pool.execute(new Eater(food));
			
		}
		
	}
	
	public void produce()
	{
		while(true)
		{
			try {
				Thread.sleep(500);
				queue.put(1);
				System.out.println("produce: " + queue.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void consume()
	{
		while(true)
		{
			try {
				Thread.sleep(500);
				queue.take();
				System.out.println("consume: " + queue.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}




class Provider implements Runnable{

	private TestArrayBlockingQueue bread;
	
	Provider(TestArrayBlockingQueue bread)
	{
		this.bread = bread;
	}

	@Override
	public void run() {
	
		bread.produce();
		
	}
}



class Eater implements Runnable{

	private TestArrayBlockingQueue bread;
	
	Eater(TestArrayBlockingQueue bread)
	{
		this.bread = bread;
	}

	@Override
	public void run() {
		
		bread.consume();
		
	}
	
}









































