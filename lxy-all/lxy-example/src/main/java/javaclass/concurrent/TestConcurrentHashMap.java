package javaclass.concurrent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {

	private Map<String, String> cmap;
	public static void main(String[] args) throws InterruptedException {
		
		TestConcurrentHashMap tchm = new TestConcurrentHashMap();
		tchm.setCmap(new ConcurrentHashMap<String, String>());
		tchm.getCmap().put("aa", "before");
		
		// Ïß³Ì2
		for(int i = 0; i < 10; i++)
		{
			new TempThread(tchm, i).start();
		}
		Thread.sleep(1);
		String value = tchm.getCmap().get("aa");
		System.out.println(value + " -2");
		
	}
	public Map<String, String> getCmap() {
		return cmap;
	}
	public void setCmap(Map<String, String> cmap) {
		this.cmap = cmap;
	}
	
	
}

class TempThread extends Thread{
	
	TestConcurrentHashMap tchm;
	int id;
	TempThread(TestConcurrentHashMap tchm, int i)
	{
		this.tchm = tchm;
		this.id = i;
		
	}
	@Override
	public void run() {

		tchm.getCmap().put("aa", "after-" + this.id);
		System.out.println(tchm.getCmap().get("aa") + " -1");
		
	}
}












