package spring.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLIBProxy implements MethodInterceptor{

	private Object target;
	CGLIBProxy(Object target)
	{
		this.target = target;
	}
	
	public Object getProxy()
	{
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object proxy, Method method, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		System.out.println("----before method!----");
		Object obj = method.invoke(target, arg2);
		System.out.println("----after method!----");
		return obj;
	}

	
}
