package designpattern.iterator;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcreteCollection<String> cc = new ConcreteCollection<String>();
		cc.add("a");
		cc.add("b");
		cc.add("c");
		cc.add("d");
		cc.add("e");
		cc.add("f");
		cc.add("g");
		
		for(Iterator<String> iterator = cc.iterator(); iterator.hastNext();)
		{
			System.out.println(iterator.next());
		}
	}

}
