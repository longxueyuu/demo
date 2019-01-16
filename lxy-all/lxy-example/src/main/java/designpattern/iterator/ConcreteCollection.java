package designpattern.iterator;

import java.util.ArrayList;

public class ConcreteCollection<T> implements Collection<T> {

	private ArrayList<T> arraylist = new ArrayList<T>();
	@Override
	public void add(T t) {
		arraylist.add(t);
	}

	@Override
	public T get(int i) {
		return arraylist.get(i);
	}

	@Override
	public int size()
	{
		return arraylist.size();
	}
	
	@Override
	public Iterator<T> iterator() {	
		return new ConcreteIterator<T>(this);
	}
}
