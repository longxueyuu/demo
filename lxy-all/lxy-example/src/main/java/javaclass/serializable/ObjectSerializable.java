package javaclass.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectSerializable {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Person person = new Person("001", "lee", 20);
		Pet dog = new Pet("dog", 2);
		person.setPet(dog);
		OutputStream fos = new FileOutputStream("ObjectSerializable.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(person);
		oos.close();
		
		FileInputStream fis = new FileInputStream("ObjectSerializable.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Person person1 = (Person)ois.readObject();
		System.out.println("ID:" + person1.getId() + " 姓名：" + person.getName() + " 年龄：" + person1.getAge());
		System.out.println("宠物：" + person.getPet().getName() + " 宠物年龄：" + person.getPet().getAge());
	}	
}

