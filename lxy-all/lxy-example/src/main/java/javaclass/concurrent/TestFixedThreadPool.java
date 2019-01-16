package javaclass.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestFixedThreadPool {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i = 0; i < 20; i++)
		{
			executor.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "ÕýÔÚÖ´ÐÐ...");
				}
			});
		}
	}
}
