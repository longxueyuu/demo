package spring.proxy.cglib;

public class TargetClass {
	
	public String doAction()
	{
		System.out.println("----执行 业务 方法----");
		return "success";
	}
}
