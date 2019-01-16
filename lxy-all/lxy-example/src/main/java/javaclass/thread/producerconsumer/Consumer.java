package javaclass.thread.producerconsumer;

/**
 * 消费面包
 * @author Administrator
 *
 */
public class Consumer implements Runnable {

	private Bread bread;
	public Consumer(Bread bread) {
		this.bread = bread;
	}
	
	@Override
	public void run() {
		for(int i = 0; i< 20; i++)
		{
				try {
					bread.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
		}
		

	}

}
