package javaclass.thread.banckaccount;

public class BankAccount {
	private int money;
	
	public BankAccount(int money) {
		this.money = money;
	}
	
	public synchronized int withdrawMoney(int num) throws InterruptedException
	{
		if(this.money >= num)
		{
			Thread.sleep(500);
			this.money = this.money - num;
			System.out.println("Óà¶î£º" + this.money);
			return this.money;
		}
		System.out.println("Óà¶î£º" + this.money);
		return -1;
	}
	
	public int storeMoney(int num)
	{
		if(num > 0)
		{
			return this.money = this.money + num;
		}
		return this.money;
	}
}
