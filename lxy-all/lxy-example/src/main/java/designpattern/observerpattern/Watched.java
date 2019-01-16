package designpattern.observerpattern;

public interface Watched {
	
	public void addWatcher(Watcher watcher);
	
	public void removeWatcher(Watcher watcher);
	
	public void notifyWatchers(Object obj);
}
