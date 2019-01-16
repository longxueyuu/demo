package javaclass.thread.banckaccount;

public class ThreadClient {
	
	public static void main(String[] args) {
		
		BankAccount ba = new BankAccount(1000);
		WithdrawThread wt = new WithdrawThread(ba, 500);
		
		Thread t1 = new Thread(wt);
		Thread t2 = new Thread(wt);
		Thread t3 = new Thread(wt);
		
		t1.start();
		t2.start();
		t3.start();
	}

}
