package javaclass.thread.banckaccount;

public class WithdrawThread implements Runnable {

	private BankAccount bankAccount;
	private int num;
	public WithdrawThread(BankAccount bankAccount, int num) {
		this.bankAccount = bankAccount;
		this.num = num;
	}
	@Override
	public void run() {
		for(int i = 0; i < 2; i++)
		{
			try {
				bankAccount.withdrawMoney(num);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
