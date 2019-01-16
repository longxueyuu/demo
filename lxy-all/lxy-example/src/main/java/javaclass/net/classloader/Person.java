package javaclass.net.classloader;

public class Person {
	public int age = 20;
	public Person(){
		System.out.println("Person: " + this.getClass().getClassLoader());
		new Pet();
	}
}
