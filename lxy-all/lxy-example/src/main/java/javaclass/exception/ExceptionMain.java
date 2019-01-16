package javaclass.exception;

public class ExceptionMain {

	/**
	 * 测试自定义异常
	 * 
	 * @param str
	 * @throws TypeException
	 */
	public void testTypeException(String str) throws TypeException
	{
		if("hello".equals(str))
		{
			throw new TypeException("输入的字符串不能为hello");
		}
		if(str == null)
		{
			throw new TypeException("输入的字符串不能为null");
		}
		return;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ExceptionMain em = new ExceptionMain();
		String str = "hello";
		try {
			System.out.println("进入Try块！");
			em.testTypeException(str);
		} catch (TypeException e) {
			System.out.println("进入TypeException Catch块！");
			e.printStackTrace();
		}
		finally{//执行了try块后，除了exit(0)退出虚拟机外，无论是否执行catch块，finally块都将被执行一次，即使在try块中有return语句也不例外
			System.out.println("进入finally块！");
		}
		System.out.println("程序结束!！");
	}

}
