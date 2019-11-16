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
		// ����InvocationHandler��invoke�����ķ���ֵӦ�����ʵ����������Ϊsub2�����õķ����ķ���ֵ���ͱ���һ��
		// ����Ҫ��method�������õķ���ֵ��Ϊinvoke�����ķ���ֵ���أ�������׳��쳣
		Object methodReturnValue = method.invoke(obj, args);
		System.out.println("after Invoke:" + method);
		
		return methodReturnValue;
	}

}
