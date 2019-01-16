package designpattern.singletonpattern;

import java.util.Collections;
import java.util.Hashtable;

public class Client {

	public static void main(String[] args) {
		ConcurrentSingleTon.otherStaticMethod();
		System.out.println("----------------");
		ConcurrentSingleTon.getInstance();
		Collections col;
		Hashtable hs;
	}
}
