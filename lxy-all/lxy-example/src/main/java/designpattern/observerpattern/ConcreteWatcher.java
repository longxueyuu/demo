package designpattern.observerpattern;

public class ConcreteWatcher implements Watcher {

	@Override
	public void update(Object obj) {

		System.out.println(obj);
	}

}
