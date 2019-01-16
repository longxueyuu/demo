package javaclass.concurrent.timertimertask;

import java.util.Timer;
import java.util.TimerTask;

public class TestTimerTask {

	
	
	
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer(true);
		Task task1 = new Task(1);
		Task task2 = new Task(2);
		Task task3 = new Task(3);
		timer.schedule(task1, 5000);
		timer.schedule(task2, 2000, 2000);
		timer.schedule(task3, 7000);
		
		
		Thread.sleep(20000);
		System.out.println("开始取消task2！");
		//task2.cancel();
		System.out.println("已经取消task2！");
		//timer.cancel();
		System.out.println("主线程结束！");
	}
}





class Task extends TimerTask{

	private int id;
	Task(int id)
	{
		this.id = id;
	}
	
	
	@Override
	public void run() {

		System.out.println("Task" + id + " is running...");
		
	}
	
}



























