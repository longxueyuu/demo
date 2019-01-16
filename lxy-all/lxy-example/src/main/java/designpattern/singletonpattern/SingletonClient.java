package designpattern.singletonpattern;

public class SingletonClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < 50; i++)
		{
			(new Thread(){
				@Override
				public void run() {
					
					Person person = Person.getInstance();
					System.out.println(person);
				}
			}).start();
		}
		
		for(int i = 0; i < 50; i++)
		{
			(new Thread(){
				@Override
				public void run() {
					
					ConcurrentSingleTon cstSingleton = ConcurrentSingleTon.getInstance();
					System.out.println(cstSingleton);
				}
			}).start();
			
		}
		
	
	}

}
