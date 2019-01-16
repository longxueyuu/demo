package javaclass.net.classloader;

public class Pet {
	Pet(){
		System.out.println("Pet: " + this.getClass().getClassLoader());
	}

}
