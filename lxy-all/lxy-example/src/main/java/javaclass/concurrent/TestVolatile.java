package javaclass.concurrent;

public class TestVolatile {
	public volatile int inc = 0;
    
    public void increase() {
        inc++;
    }
     
    public static void main(String[] args) {
        final TestVolatile test = new TestVolatile();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                    try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
        {   
        	Thread.yield();
        	System.out.println(Thread.activeCount());
        }
        System.out.println(test.inc);
    }
}
