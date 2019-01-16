package javaclass.concurrent.LatchBarrierSemaphore;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {

	 public static void main(String[] args) throws InterruptedException {
	        int N = 8;
	        CountDownLatch latch = new CountDownLatch(8);
	        CyclicBarrier barrier  = new CyclicBarrier(N, new Runnable() {
				public void run() {
					System.out.println("当所有的写操作做完之后，从运行的线程中选择一个线程来执行runnable中的动作！选择了" + Thread.currentThread().getName());
				}
			});
	        
	        for(int i = 0;i < (N / 2);i++)
	        {
	            new WriterA(barrier, latch).start();
	        	new WriterB(barrier, latch).start();
	        }
	        System.out.println("等待所有线程完成！");
	        latch.await();
	        System.out.println("所有线程执行完毕");
	    }
	 
	 
	 	// 内部线程类
	    static class WriterA extends Thread{
	        private CyclicBarrier cyclicBarrier;
	        private CountDownLatch latch;
	        public WriterA(CyclicBarrier cyclicBarrier, CountDownLatch latch) {
	            this.cyclicBarrier = cyclicBarrier;
	            this.latch = latch;
	        }
	 
	        @Override
	        public void run() {
	            System.out.println("线程A"+Thread.currentThread().getName()+"正在写入数据...");
	            try {
	                Thread.sleep((long)Math.random() * 5000);      //以睡眠来模拟写入数据操作
	                System.out.println("线程A"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
	                cyclicBarrier.await();
	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }catch(BrokenBarrierException e){
	                e.printStackTrace();
	            }
	            System.out.println("所有WriterA线程执行完毕");
	            latch.countDown();
	        }
	    }
	    
	    // 内部线程类
	    static class WriterB extends Thread{
	        private CyclicBarrier cyclicBarrier;
	        private CountDownLatch latch;
	        public WriterB(CyclicBarrier cyclicBarrier, CountDownLatch latch) {
	            this.cyclicBarrier = cyclicBarrier;
	            this.latch = latch;
	        }
	 
	        @Override
	        public void run() {
	            System.out.println("线程B"+Thread.currentThread().getName()+"正在写入数据...");
	            try {
	                Thread.sleep((long)Math.random() * 5000);      //以睡眠来模拟写入数据操作
	                System.out.println("线程B"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
	                cyclicBarrier.await();
	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }catch(BrokenBarrierException e){
	                e.printStackTrace();
	            }
	            System.out.println("所有WriterB线程执行完毕");
	            latch.countDown();
	        }
	    }
}
