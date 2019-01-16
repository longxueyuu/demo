package spring.proxy.cglib;

import java.util.ArrayList;

import org.junit.Test;

public class TestCGLIBProxy {

	@Test
	public void testProxy()
	{
		TargetClass target = new TargetClass();
		CGLIBProxy cglibProxy = new CGLIBProxy(target);
		TargetClass proxy = (TargetClass)cglibProxy.getProxy();
		proxy.doAction();
		
		/* final 类 不能使用cglib进行代理
		String str = "aanncc";
		String strProxy = (String)new CGLIBProxy(str).getProxy();
		System.out.println(strProxy.replaceAll("n", "b"));
		*/
		
		ArrayList list = new ArrayList();
		ArrayList listProxy = (ArrayList)new CGLIBProxy(list).getProxy();
		listProxy.add("a");
		listProxy.add("b");
	}
}
