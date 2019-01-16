package javaclass.thread.producerconsumer;

/**
 * 生产面包
 * @author Administrator
 *
 */
public class Producer extends Thread {
	
	private Bread bread;
	Producer(Bread bread)
	{
		this.bread = bread;
	}
	
	
	@Override
	public void run(){
		for(int i = 0; i< 20; i++)
		{
				try {
					bread.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
		}
		
	}
	
}
