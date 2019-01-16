package javaclass.cloneable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class Person implements Cloneable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6798335608769890003L;
	private String id;
	private String name;
	private int age;
	public Pet pet;
	
	/**
	 * 浅复制对象
	 */
	@Override
	public Person clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Person)super.clone();
	}
	
	public Person deepClone() throws IOException, ClassNotFoundException
	{
		//通过序列化将对象写入字节流
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(this);
		
		
		InputStream is = new ByteArrayInputStream(os.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(is);
		return (Person)ois.readObject();
	}
	

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	Person(String id, String name, int age){
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
