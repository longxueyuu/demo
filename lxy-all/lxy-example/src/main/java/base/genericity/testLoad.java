package base.genericity;

class Child{
	// ����final���Σ�a�ͱ�ɳ����ˣ�����a�Ͳ�������ʹ��Child��
	//public static final int a = 3;
	// ����final���Σ�a���Ǳ���������a������ʹ��Child��
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
