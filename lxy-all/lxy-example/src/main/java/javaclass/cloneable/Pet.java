package javaclass.cloneable;

import java.io.Serializable;

public class Pet implements Cloneable, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3487010602282255168L;
	private String name;
	private int age;
	
	Pet(String name, int age)
	{
		this.name = name;
		this.age = age;
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
