package designpattern.dynamicproxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {
	
	private Object obj;
	public DynamicSubject(Object obj){
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("before Invoke:" + method);
		// 由于InvocationHandler的invoke方法的返回值应与代理实例（本例中为sub2）调用的方法的返回值类型保持一致
		// 所以要将method方法调用的返回值作为invoke方法的返回值返回，否则会抛出异常
		Object methodReturnValue = method.invoke(obj, args);
		System.out.println("after Invoke:" + method);
		
		return methodReturnValue;
	}

}
