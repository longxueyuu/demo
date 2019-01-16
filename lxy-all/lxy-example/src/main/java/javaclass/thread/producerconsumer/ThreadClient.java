package javaclass.thread.producerconsumer;

public class ThreadClient {
	
	public static void main(String[] args) {
		Bread bread = new Bread();
		
		
		Consumer consumer = new Consumer(bread);
		Thread producerThread1 = new Producer(bread);
		Thread producerThread2 = new Producer(bread);
		Thread producerThread3 = new Producer(bread);
		Thread producerThread4 = new Producer(bread);
		
		Thread consumerThread1  = new Thread(consumer);
		Thread consumerThread2  = new Thread(consumer);
		Thread consumerThread3  = new Thread(consumer);
		
		producerThread1.start();
		producerThread2.start();
		consumerThread1.start();
		consumerThread2.start();
		consumerThread3.start();
		
		
	}
	
	
	
	
	
}
