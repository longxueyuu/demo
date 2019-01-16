package designpattern.dynamicproxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DynamicProxyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 生成的realSubject将通过DynamicSubject的构造方法传人hangdler的invoke方法，实现代理
		AbstractSubject realSubject= new RealSubject();
		InvocationHandler handler = new DynamicSubject(realSubject);
		
		Class<?> classType = realSubject.getClass();// 取得realSubject对象运行时对应类的 Class 对象
		
		// 生成代理对象 sub
		//AbstractSubject sub = (AbstractSubject)Proxy.newProxyInstance(classType.getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		
		AbstractSubject sub = (AbstractSubject)Proxy.newProxyInstance(classType.getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		
		//调用该句时，程序即转向handler中的invoke方法
		sub.request(1, 2);//对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法
					
		System.out.println(sub.getClass() + "*----_----*");
		
		// 代理 java原生类
		Set hasnsetObj  = new HashSet();
		InvocationHandler handler2 = new DynamicSubject(hasnsetObj);
		
		Class<?> classType2 = hasnsetObj.getClass();// 取得hasnsetObj对象运行时对应类的 Class 对象
		
		// 生成代理对象 sub2
		Set sub2 = (Set)Proxy.newProxyInstance(classType2.getClassLoader(), hasnsetObj.getClass().getInterfaces(), handler2);
		//System.out.println(sub2);
		
		//调用该句时，程序即转向handler中的invoke方法
		// InvocationHandler的invoke方法的返回值应与sub2调用的方法的返回值类型保持一致
		sub2.add(1);//对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法
		sub2.add(2);//对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法
		sub2.add("string");
		sub2.toArray();
		sub2.clear();
		for(Iterator iterator = hasnsetObj.iterator(); iterator.hasNext();){
			System.out.println(iterator.next());
		}
		
	}

}
