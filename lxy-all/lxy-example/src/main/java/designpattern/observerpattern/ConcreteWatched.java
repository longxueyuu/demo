package designpattern.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class ConcreteWatched implements Watched {
	
	List<Watcher> watchersList = new ArrayList<Watcher>();

	@Override
	public void addWatcher(Watcher watcher) {
		
		watchersList.add(watcher);
	}

	@Override
	public void removeWatcher(Watcher watcher) {
		
		watchersList.remove(watcher);
	}

	@Override
	public void notifyWatchers(Object obj) {
		
		for(Watcher watcher : watchersList){
			watcher.update(obj);
		}
	}


}
