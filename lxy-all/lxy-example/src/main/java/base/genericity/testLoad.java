package base.genericity;

class Child{
	// 加上final修饰，a就变成常量了，访问a就不是主动使用Child类
	//public static final int a = 3;
	// 不加final修饰，a就是变量，访问a是主动使用Child类
	public static int a = 3;
	static{
		System.out.println("child static block");
	}
}

public class testLoad {
	
	static{
		System.out.println("testLoad static block");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
			System.out.println("a:" + Child.a);

	}

}
