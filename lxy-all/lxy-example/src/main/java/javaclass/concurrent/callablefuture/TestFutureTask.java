package javaclass.concurrent.callablefuture;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestFutureTask {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		
		Set<Task> tasks = new HashSet<Task>();
		for(int i = 0; i < 20; i++)
		{
			tasks.add(new Task(i));
		}
		
		
		List<Future<Integer>> futures = pool.invokeAll(tasks);
		pool.shutdown();
		
		System.out.println("主线程开始获取子线程计算结果...");
		try {
			for(int i = 0; i < futures.size(); i++)
			{
				int result = futures.get(i).get();
				System.out.println(result);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}





class Task implements Callable<Integer>{
	
	private int number;
	Task(int number)
	{
		this.number = number;
	}
	@Override
	public Integer call() throws Exception {
		
		System.out.println(Thread.currentThread().getName() + "正在计算");
		Thread.sleep(2000);
		int sum = 0;
		for(int i = 0; i <= number; i++)
		{
			sum += i;
		}
		return sum;
	}
	
	
}























