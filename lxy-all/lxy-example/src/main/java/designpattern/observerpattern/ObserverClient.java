package designpattern.observerpattern;

public class ObserverClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ���۲�� �����������
		Watched watchedObj = new ConcreteWatched();
		
		// ����Ĺ۲��߶���
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
