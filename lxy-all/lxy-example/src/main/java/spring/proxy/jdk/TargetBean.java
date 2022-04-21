package spring.proxy.jdk;

public class TargetBean implements ITarget {

	@Override
	public String doAction() {
		System.out.println("----执行业务方法----");
		return "success";
	}

}
