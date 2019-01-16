package javaclass.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadExecutor {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		for(int i = 0; i < 10; i++)
		{
			executor.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "ÕýÔÚÖ´ÐÐ...");
				}
			});
		}
		executor.shutdown();
	}
}
