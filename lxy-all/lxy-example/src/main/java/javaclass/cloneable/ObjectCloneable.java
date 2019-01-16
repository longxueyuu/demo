package javaclass.cloneable;

import java.io.IOException;

public class ObjectCloneable {

	/**
	 * @param args
	 * @throws CloneNotSupportedException 
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
		Person person = new Person("002", "levis", 24);
		Pet cat = new Pet("cat", 4);
		person.setPet(cat);
		//
		//对象浅克隆
		Person clonePerson = person.clone();
		clonePerson.setId("00x");
		clonePerson.setName("cloneLevis");
		System.out.println("原始人：" + person.getName() + " ID:" + person.getId() + " 宠物名：" + person.getPet().getName() + " 宠物年龄" + person.getPet().getAge());
		System.out.println("复制的人：" + clonePerson.getName() + " ID:" + clonePerson.getId() + " 宠物名：" + clonePerson.getPet().getName() + " 宠物年龄：" + clonePerson.getPet().getAge());
		cat.setName("dog");
		cat.setAge(5);
		clonePerson.getPet().setName("catdog");
		clonePerson.getPet().setAge(100);
		System.out.println("\n-----------------------------\n");
		System.out.println("原始人：" + person.getName() + " ID:" + person.getId() + " 宠物名：" + person.getPet().getName() + " 宠物年龄" + person.getPet().getAge());
		System.out.println("复制的人：" + clonePerson.getName() + " ID:" + clonePerson.getId() + " 宠物名：" + clonePerson.getPet().getName() + " 宠物年龄：" + clonePerson.getPet().getAge());
	
		System.out.println("\n************************\n");
		
		//对象深克隆
		Person person2 = new Person("009", "lee", 30);
		Pet tiger = new Pet("tiger", 9);
		person2.setPet(tiger);
		Person deepClonePerson = person2.deepClone();
		deepClonePerson.setId("900");
		deepClonePerson.setName("newLee");
		System.out.println("原始人：" + person2.getName() + " ID:" + person2.getId() + " 宠物名：" + person2.getPet().getName() + " 宠物年龄" + person2.getPet().getAge());
		System.out.println("复制的人：" + deepClonePerson.getName() + " ID:" + deepClonePerson.getId() + " 宠物名：" + deepClonePerson.getPet().getName() + " 宠物年龄：" + deepClonePerson.getPet().getAge());
		tiger.setName("I'm lion now!");
		tiger.setAge(10);
		deepClonePerson.getPet().setName("I'm wolf now!");
		deepClonePerson.getPet().setAge(11);
		System.out.println("\n-----------------------------\n");
		System.out.println("原始人：" + person2.getName() + " ID:" + person2.getId() + " 宠物名：" + person2.getPet().getName() + " 宠物年龄" + person2.getPet().getAge());
		System.out.println("复制的人：" + deepClonePerson.getName() + " ID:" + deepClonePerson.getId() + " 宠物名：" + deepClonePerson.getPet().getName() + " 宠物年龄：" + deepClonePerson.getPet().getAge());
	
	}

}
