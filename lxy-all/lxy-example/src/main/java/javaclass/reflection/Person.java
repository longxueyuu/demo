package javaclass.reflection;

public class Person {
	String name;
	private int age;
	public Person(){
		
	}
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	Person(String name){
		this.name = name;
		this.age = -1;
	}
	
	private String changeName(String newName){
		
		this.name = newName;
		return this.name;
	}
	
	public void printInformation(){
		System.out.println(this.name + ":" + this.age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
