package javaclass.thread.producerconsumer;

public class Bread {
	private  int quantity = 0;
	
	public synchronized void produce() throws InterruptedException
	{
		Thread.sleep(100);
		while(quantity >= 5)
		{
			wait();
		}
		quantity++;
		System.out.println("生产后数量：" + this.quantity);
		notifyAll();
		
		
		
	}

	public synchronized void consume() throws InterruptedException
	{
		Thread.sleep(100);
		while(quantity <= 0)
		{
			wait();
		}
		quantity--;
		System.out.println("消费后数量：" +  this.quantity);
		notifyAll();
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
