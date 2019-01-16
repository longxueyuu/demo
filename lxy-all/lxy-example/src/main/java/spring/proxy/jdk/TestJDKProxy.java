package spring.proxy.jdk;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestJDKProxy {

	@Test
	public void testProxy()
	{
		ITarget target = new TargetBean();
		JDKProxy jdkProxy = new JDKProxy(target);
		target = (ITarget)jdkProxy.getProxy();
		target.doAction();
		
		CharSequence str = new String("aabbcc");
		str = (CharSequence)(new JDKProxy(str).getProxy());
		System.out.println(str.length());
		
		
		Set set = new HashSet();
		set = (Set)new JDKProxy(set).getProxy();
		set.add("a");
		
	}
}
