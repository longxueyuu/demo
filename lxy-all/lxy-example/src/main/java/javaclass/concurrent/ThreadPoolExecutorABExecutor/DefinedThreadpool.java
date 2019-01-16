package javaclass.concurrent.ThreadPoolExecutorABExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class DefinedThreadpool extends ThreadPoolExecutor {

 private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
 private final AtomicLong numTasks = new AtomicLong();
 private final AtomicLong totalTime = new AtomicLong();
 
 public DefinedThreadpool(int corePoolSize, int maximumPoolSize,
   long keepAliveTime, TimeUnit unit, BlockingQueue workQueue) {
  super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
 }

 @Override
 protected void afterExecute(Runnable r, Throwable t) {
  try{
   long endTime = System.currentTimeMillis();
   long useTime = endTime - startTime.get();
   numTasks.incrementAndGet();
   totalTime.addAndGet(useTime);
   System.out.println("afterExecute " + r);
  }finally{
   super.afterExecute(r, t);
  }
 }

 @Override
 protected void beforeExecute(Thread t, Runnable r) {
  super.beforeExecute(t, r);
  System.out.println("beforeExecute " + r);
  startTime.set(System.currentTimeMillis());
 }

 @Override
 protected void terminated() {
  try{
   System.out.println("terminated avg time " + totalTime.get()  + " " + numTasks.get());
  }finally{
   super.terminated();
  }
 }
 
 

}

