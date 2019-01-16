package designpattern.observerpattern;

public class ObserverClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 被观察的 具体主题对象
		Watched watchedObj = new ConcreteWatched();
		
		// 具体的观察者对象
		Watcher watcher1 = new ConcreteWatcher();
		Watcher watcher2 = new ConcreteWatcher();
		Watcher watcher3 = new ConcreteWatcher();
		
		watchedObj.addWatcher(watcher1);
		watchedObj.addWatcher(watcher2);
		watchedObj.addWatcher(watcher3);
		
		watchedObj.notifyWatchers("please watch me!");
		
		watchedObj.removeWatcher(watcher2);
		watchedObj.notifyWatchers("Thanks for you watching!");
		
		
		

	}

}
