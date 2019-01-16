package javaclass.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class ReflectionInvokeMethod {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		// TODO Auto-generated method stub
		/**
		 * ��ȡClass��������ַ�ʽ
		 */	
		// ��һ�� ����Class�ľ�̬����forName(String className)
	    Class<?> strClassObj = Class.forName("java.lang.String");
	    
	    // �ڶ��� ���� ����.class
	    Class strClassObj2 = String.class;
	    
	    // ������ ���ʵ����getClass()����
	    String author = "lxy";
	    Class strClassObj3 = author.getClass();
	    
	    System.out.println(strClassObj.getName() + "\n" + strClassObj2.getName() + "\n" +strClassObj3.getName() + "\n" );
	    
	    Method[] strClassMethod = strClassObj.getMethods();
		for(Method method : strClassMethod){
			//System.out.println(method);
		}
		
		Constructor strClassConstructor = strClassObj.getConstructor(new Class[]{String.class});
		String strName = (String)strClassConstructor.newInstance("lxy");
		
		Method strSubString = strClassObj.getMethod("substring", new Class[]{int.class, int.class});
		Object substringOfStrName = strSubString.invoke(strName, new Integer[]{3,7});
		System.out.println(substringOfStrName);
		System.out.println("end");
		
		Person personA = new Person("zhangsan", 60);
		personA.printInformation();
		
		Class personClass = Class.forName("javaclass.reflection.Person");
		System.out.println("Person������ " + personClass.getName());
		
		Constructor personConstructor = personClass.getConstructor(new Class[]{String.class, int.class});
		Object personB = personConstructor.newInstance("lisi", 100);
		Method personPublicMethodPrint = personClass.getMethod("printInformation", new Class[]{});
		personPublicMethodPrint.invoke(personB, null);
		
		Constructor personNonPublicConstructor = personClass.getDeclaredConstructor(new Class[]{String.class});
		Object personC = personNonPublicConstructor.newInstance("wangwu");
		
		// ����Person��public����
		Method personPublicMethodPrint2 = personClass.getMethod("printInformation", new Class[]{});
		personPublicMethodPrint.invoke(personC, null);
		
		// ����Person��˽�з���changeName(String newName)
		Method personPrivateMethod = personClass.getDeclaredMethod("changeName", new Class[]{String.class});
		personPrivateMethod.setAccessible(true);// ����˽�з����Ĺؼ�һ�仰
		Object newName = personPrivateMethod.invoke(personC, "SB WangWu");
		System.out.println(newName);
		
		// ��ȡPerson���˽�б���
		Field personPrivateField = personClass.getDeclaredField("age");
		personPrivateField.setAccessible(true);// ����˽�б����Ĺؼ�һ�仰
		personPrivateField.set(personC, -100);
		// ��ӡ���ĺ��˽�б���ֵ
		personPublicMethodPrint.invoke(personC, null);
	}

}
