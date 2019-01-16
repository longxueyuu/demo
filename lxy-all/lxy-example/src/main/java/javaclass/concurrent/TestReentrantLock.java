package javaclass.concurrent;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
public class TestReentrantLock extends Thread{
	CriticalResource lock;
private int id;
public TestReentrantLock(int i, CriticalResource test){
    this.id=i;
    this.lock=test;
}
public void run(){
    lock.print(id);
}
public static void main(String args[]){
    ExecutorService service=Executors.newCachedThreadPool();
    CriticalResource cr=new CriticalResource();
    for(int i=0;i<10;i++){
     service.submit(new TestReentrantLock(i, cr));
    }
    service.shutdown();
}
}
class CriticalResource{
private ReentrantLock lock=new ReentrantLock();
public void print(int str){
	 //ReentrantReadWriteLock
    try{
     lock.lock();
     System.out.println(str+"»ñµÃ");
     Thread.sleep((int)(Math.random()*1000));
     
    }
    catch(Exception e){
     e.printStackTrace();
    }
    finally{
     System.out.println(str+"ÊÍ·Å");
     lock.unlock();
    }
}
}