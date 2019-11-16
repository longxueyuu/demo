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

		// ���ɵ�realSubject��ͨ��DynamicSubject�Ĺ��췽������hangdler��invoke������ʵ�ִ���
		AbstractSubject realSubject= new RealSubject();
		InvocationHandler handler = new DynamicSubject(realSubject);
		
		Class<?> classType = realSubject.getClass();// ȡ��realSubject��������ʱ��Ӧ��� Class ����
		
		// ���ɴ������ sub
		//AbstractSubject sub = (AbstractSubject)Proxy.newProxyInstance(classType.getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		
		AbstractSubject sub = (AbstractSubject)Proxy.newProxyInstance(classType.getClassLoader(), realSubject.getClass().getInterfaces(), handler);
		
		//���øþ�ʱ������ת��handler�е�invoke����
		sub.request(1, 2);//�Դ���ʵ�����÷���ʱ�����Է������ý��б��벢����ָ�ɵ����ĵ��ô������� invoke ����
					
		System.out.println(sub.getClass() + "*----_----*");
		
		// ���� javaԭ����
		Set hasnsetObj  = new HashSet();
		InvocationHandler handler2 = new DynamicSubject(hasnsetObj);
		
		Class<?> classType2 = hasnsetObj.getClass();// ȡ��hasnsetObj��������ʱ��Ӧ��� Class ����
		
		// ���ɴ������ sub2
		Set sub2 = (Set)Proxy.newProxyInstance(classType2.getClassLoader(), hasnsetObj.getClass().getInterfaces(), handler2);
		//System.out.println(sub2);
		
		//���øþ�ʱ������ת��handler�е�invoke����
		// InvocationHandler��invoke�����ķ���ֵӦ��sub2���õķ����ķ���ֵ���ͱ���һ��
		sub2.add(1);//�Դ���ʵ�����÷���ʱ�����Է������ý��б��벢����ָ�ɵ����ĵ��ô������� invoke ����
		sub2.add(2);//�Դ���ʵ�����÷���ʱ�����Է������ý��б��벢����ָ�ɵ����ĵ��ô������� invoke ����
		sub2.add("string");
		sub2.toArray();
		sub2.clear();
		for(Iterator iterator = hasnsetObj.iterator(); iterator.hasNext();){
			System.out.println(iterator.next());
		}
		
	}

}
