package javaclass.concurrent.ThreadPoolExecutorABExecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DefinedThreadPoolDemo {

 public static void main(String[] args) {
	 
	 
	 
  DefinedThreadpool pool = new DefinedThreadpool(2, 2, 0L, TimeUnit.MILLISECONDS,
    new LinkedBlockingQueue(10));
  for(int i=0;i<3;i++){
   pool.execute(new MyThread());
  }
  pool.shutdown();
 }
 
 static class MyThread extends Thread{
  public void run(){
   System.out.println("run "+Thread.currentThread().getName());
   try {
    Thread.sleep(100);
   } catch (InterruptedException e) {
    e.printStackTrace();
   }
  }
 }
}


